package com.horizon.mapper;

import com.horizon.domain.Payment;
import com.horizon.dto.PaymentTransactionDto;

import java.sql.Timestamp;

public interface PaymentMapper {
    PaymentTransactionDto toPaymentDto(Payment payment);

    Payment toPayment(PaymentTransactionDto paymentTransactionDto);

    Timestamp toTimestamp(String time);

    String toTimeString(Timestamp timestamp);
}
