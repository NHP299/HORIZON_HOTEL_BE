package com.horizon.service;


import com.horizon.domain.Booking;
import com.horizon.domain.Payment;
import com.horizon.dto.PaymentDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface PaymentService {
    PaymentDTO.VNPayResponse createVnPayPayment(HttpServletRequest request);

    Payment create(HttpServletRequest request, Booking booking);
}
