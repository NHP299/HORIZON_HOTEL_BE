package com.horizon.mapper;

import com.horizon.domain.Payment;
import com.horizon.dto.PaymentDto;

public interface PaymentMapper {
    PaymentDto toPaymentDto(Payment payment);

    Payment toPayment(PaymentDto paymentDto);
}
