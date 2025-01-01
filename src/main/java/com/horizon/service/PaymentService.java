package com.horizon.service;


import com.horizon.domain.Booking;
import com.horizon.domain.Payment;
import com.horizon.dto.PaymentDTO;
import com.horizon.dto.PaymentTransactionDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface PaymentService {
    PaymentDTO.VNPayResponse createVnPayPayment(HttpServletRequest request, Double bookingPrice);

    Payment createCashPayment(Booking booking);

    Payment create(String url, Booking booking) throws UnsupportedEncodingException;

    void updateSuccessPayment(HttpServletRequest request);

    List<PaymentTransactionDto> getAll();

    void updateFailPayment(HttpServletRequest request);

    @Scheduled(fixedRate = 60000)
    void updateFailPayment();
}
