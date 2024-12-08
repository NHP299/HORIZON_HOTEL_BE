package com.horizon.service;


import com.horizon.domain.Booking;
import com.horizon.domain.Payment;
import com.horizon.dto.PaymentDTO;
import com.horizon.dto.PaymentTransactionDto;
import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface PaymentService {
    PaymentDTO.VNPayResponse createVnPayPayment(HttpServletRequest request);

    Payment create(String url, Booking booking) throws UnsupportedEncodingException;

    void updateSuccessPayment(HttpServletRequest request);

    List<PaymentTransactionDto> getAll();

}
