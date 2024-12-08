package com.horizon.service;


import com.horizon.domain.Booking;
import com.horizon.domain.Payment;
import com.horizon.dto.PaymentDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;

public interface PaymentService {
    PaymentDTO.VNPayResponse createVnPayPayment(HttpServletRequest request);

    Payment create(String url, Booking booking) throws UnsupportedEncodingException;

    void updateSuccessPayment(HttpServletRequest request);
}
