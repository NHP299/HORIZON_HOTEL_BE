package com.horizon.service.impl;

import com.horizon.config.VNPAYConfig;
import com.horizon.domain.Booking;
import com.horizon.domain.Payment;
import com.horizon.dto.PaymentDTO;
import com.horizon.mapper.PaymentMapper;
import com.horizon.repository.PaymentRepository;
import com.horizon.service.PaymentService;
import com.horizon.util.VNPayUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final VNPAYConfig vnPayConfig;

    private final PaymentMapper paymentMapper;

    @Override
    public PaymentDTO.VNPayResponse createVnPayPayment(HttpServletRequest request) {
        long amount = Integer.parseInt(request.getParameter("amount")) * 100L;
        String bankCode = request.getParameter("bankCode");
        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig();
        vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
        if (bankCode != null && !bankCode.isEmpty()) {
            vnpParamsMap.put("vnp_BankCode", bankCode);
        }
        vnpParamsMap.put("vnp_IpAddr", VNPayUtil.getIpAddress(request));
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
    public Payment create(HttpServletRequest request, Booking booking) {
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setTransactionId(request.getParameter("vnp_TxnRef"));
        payment.setAmount(Double.valueOf(request.getParameter("vnp_Amount")));
        payment.setStatus(1);
        payment.setPaymentTime(paymentMapper.toTimestamp(request.getParameter("vnp_PayDate")));
        payment.setCreatedTime(paymentMapper.toTimestamp(request.getParameter("vnp_CreateDate")));
        payment.setExpiredTime(paymentMapper.toTimestamp(request.getParameter("vnp_ExpireDate")));
        payment.setDescription(request.getParameter("vnp_OrderInfo"));
        payment.setPaymentMethod(request.getParameter("vnp_Command"));
        payment.setBankCode(request.getParameter("vnp_BankCode"));
        paymentRepository.save(payment);
        return payment;
    }
}
