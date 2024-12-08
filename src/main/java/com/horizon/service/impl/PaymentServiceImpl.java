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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
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
    public Payment create(String url, Booking booking) throws UnsupportedEncodingException {
        Map<String, String> params = getParameter(url);
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setTransactionId(params.get("vnp_TxnRef"));
        payment.setAmount(Double.valueOf(params.get("vnp_Amount")));
        payment.setStatus(1);
        payment.setCreatedTime(paymentMapper.toTimestamp(params.get("vnp_CreateDate")));
        payment.setExpiredTime(paymentMapper.toTimestamp(params.get("vnp_ExpireDate")));
        payment.setDescription(params.get("vnp_OrderInfo"));
        payment.setPaymentMethod(params.get("vnp_Command"));
        payment.setBankCode(params.get("vnp_BankCode"));
        paymentRepository.save(payment);
        return payment;
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
        payment.setStatus(2);
        payment.setPaymentTime(paymentMapper.toTimestamp(request.getParameter("vnp_PayDate")));
        payment.setCardType(request.getParameter("vnp_CardType"));
        payment.setExtraData("BankTranNo: " + request.getParameter("vnp_BankTranNo")
                + " - TransactionNo: " + request.getParameter("vnp_TransactionNo")
                +" - TransactionStatus: " + request.getParameter("vnp_TransactionStatus"));
        paymentRepository.save(payment);
    }


}
