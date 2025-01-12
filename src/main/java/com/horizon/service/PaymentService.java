package com.horizon.service;


import com.horizon.domain.Booking;
import com.horizon.domain.Payment;
import com.horizon.dto.PaymentDTO;
import com.horizon.dto.PaymentTransactionDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface PaymentService {
    PaymentDTO.VNPayResponse createVnPayPayment(HttpServletRequest request, Double bookingPrice);

    PaymentTransactionDto createCashPayment(Booking booking);

    PaymentTransactionDto create(String url, Booking booking) throws UnsupportedEncodingException;

    PaymentTransactionDto update(Integer id,PaymentTransactionDto paymentTransactionDto);

    void updateSuccessPayment(HttpServletRequest request);

    Page<PaymentTransactionDto> getAll(Pageable pageable);

    void updateFailPayment(HttpServletRequest request);

}
