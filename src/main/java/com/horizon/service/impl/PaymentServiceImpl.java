package com.horizon.service.impl;

import com.horizon.config.VNPAYConfig;
import com.horizon.domain.Booking;
import com.horizon.domain.Payment;
import com.horizon.dto.PaymentDTO;
import com.horizon.dto.PaymentTransactionDto;
import com.horizon.mapper.PaymentMapper;
import com.horizon.repository.BookingRepository;
import com.horizon.repository.PaymentRepository;
import com.horizon.service.PaymentService;
import com.horizon.util.VNPayUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final VNPAYConfig vnPayConfig;
    private final PaymentMapper paymentMapper;
    private final BookingRepository bookingRepository;

    @Override
    public PaymentDTO.VNPayResponse createVnPayPayment(HttpServletRequest request, Double bookingPrice) {
        long amount = (long) (Double.parseDouble(String.valueOf(bookingPrice)) * 100L);
        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig();
        vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
        vnpParamsMap.put("vnp_BankCode", "NCB");
        vnpParamsMap.put("vnp_IpAddr", VNPayUtil.getIpAddress(request));
        vnpParamsMap.put("vnp_TxnRef", VNPayUtil.getRandomNumber(8));
        vnpParamsMap.put("vnp_OrderInfo", "Thanh toan don hang: " + VNPayUtil.getRandomNumber(8));
        String queryUrl = VNPayUtil.getPaymentURL(vnpParamsMap, true);
        String hashData = VNPayUtil.getPaymentURL(vnpParamsMap, false);
        String vnpSecureHash = VNPayUtil.hmacSHA512(vnPayConfig.getSecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String paymentUrl = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;
        return PaymentDTO.VNPayResponse.builder()
                .code("ok")
                .message("success")
                .paymentUrl(paymentUrl).build();
    }

    @Override
    public PaymentTransactionDto createCashPayment(Booking booking) {
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(booking.getTotalPrice());
        payment.setTransactionId(VNPayUtil.getRandomNumber(8));
        payment.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        payment.setExpiredTime(Timestamp.valueOf(booking.getCheckIn().atTime(11, 59, 59)));
        payment.setStatus(Payment.Status.PENDING);
        payment.setPaymentMethod("cash");
        payment.setDescription("Thanh toan don hang: " + booking.getId());
        paymentRepository.save(payment);
        return paymentMapper.toPaymentDto(payment);
    }

    @Override
    public PaymentTransactionDto create(String url, Booking booking) throws UnsupportedEncodingException {
        Map<String, String> params = getParameter(url);
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setTransactionId(params.get("vnp_TxnRef"));
        payment.setAmount(Double.valueOf(params.get("vnp_Amount")));
        payment.setStatus(Payment.Status.PENDING);
        payment.setCreatedTime(paymentMapper.toTimestamp(params.get("vnp_CreateDate")));
        payment.setExpiredTime(paymentMapper.toTimestamp(params.get("vnp_ExpireDate")));
        payment.setDescription(params.get("vnp_OrderInfo"));
        payment.setPaymentMethod(params.get("vnp_Command"));
        payment.setBankCode(params.get("vnp_BankCode"));
        paymentRepository.save(payment);
        return paymentMapper.toPaymentDto(payment);
    }

    @Override
    public PaymentTransactionDto update(Integer id, PaymentTransactionDto paymentTransactionDto) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
        if (existingPayment.getPaymentMethod().equalsIgnoreCase("cash")) {
            existingPayment.setStatus(paymentTransactionDto.getStatus());
            return paymentMapper.toPaymentDto(paymentRepository.save(existingPayment));
        }
        return null;
    }

    private Map<String, String> getParameter(String url) throws UnsupportedEncodingException {
        String queryString = url.substring(url.indexOf("?") + 1);
        Map<String, String> queryPairs = new HashMap<>();
        String[] pairs = queryString.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            String key = URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8);
            String value = URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8);
            queryPairs.put(key, value);
        }
        return queryPairs;
    }

    @Override
    public void updateSuccessPayment(HttpServletRequest request) {
        Payment payment = paymentRepository.findByTransactionId(request.getParameter("vnp_TxnRef"));
        if (payment == null) {
            return;
        }
        payment.setStatus(Payment.Status.SUCCESS);
        payment.setPaymentTime(paymentMapper.toTimestamp(request.getParameter("vnp_PayDate")));
        payment.setCardType(request.getParameter("vnp_CardType"));
        payment.setExtraData("BankTranNo: " + request.getParameter("vnp_BankTranNo")
                + " - TransactionNo: " + request.getParameter("vnp_TransactionNo")
                + " - TransactionStatus: " + request.getParameter("vnp_TransactionStatus"));
        paymentRepository.save(payment);
    }

    @Override
    public Page<PaymentTransactionDto> getAll(Pageable pageable) {
        return paymentRepository.findAll(pageable)
                .map(paymentMapper::toPaymentDto);
    }

    @Override
    public void updateFailPayment(HttpServletRequest request) {
        Payment payment = paymentRepository.findByTransactionId(request.getParameter("vnp_TxnRef"));
        if (payment == null) {
            return;
        }
        payment.setStatus(Payment.Status.FAILED);
        payment.setExtraData("TransactionNo: " + request.getParameter("vnp_TransactionNo")
                + " - Message: " + request.getParameter("vnp_Message"));
        paymentRepository.save(payment);
    }

    @Scheduled(fixedRate = 60000)
    public void updateFailPayment() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        List<Payment> expiredPayments = paymentRepository.findExpiredPayments(currentTimestamp);

        expiredPayments.forEach(payment -> {
            payment.setStatus(Payment.Status.FAILED);
            paymentRepository.save(payment);
        });

        System.out.println("Expired payments updated.");
    }


    @Scheduled(fixedRate = 60000)
    public void updateFailPaymentByBooking() {
        List<Booking> bookings = bookingRepository.findAll().stream()
                .filter(booking -> booking.getPayment().getStatus().equals(Payment.Status.FAILED)).toList();

        List<Payment> payment = bookings.stream().map(Booking::getPayment).toList();

        payment.forEach(p -> {
            p.setStatus(Payment.Status.FAILED);
            paymentRepository.save(p);
        });

        System.out.println("Expired payments updated.");
    }


}
