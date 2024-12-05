package com.horizon.mapper.impl;

import com.horizon.domain.Payment;
import com.horizon.domain.status.PaymentStatus;
import com.horizon.dto.PaymentDto;
import com.horizon.mapper.PaymentMapper;
import com.horizon.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentMapperImpl implements PaymentMapper {

    private final BookingRepository bookingRepository;

    @Override
    public PaymentDto toPaymentDto(Payment payment) {
        if (payment == null) {
            return null;
        }

        PaymentDto dto = new PaymentDto();
        dto.setId(payment.getId());
        dto.setBookingId(payment.getBooking().getId());
        dto.setTransactionId(payment.getTransactionId());
        dto.setAmount(payment.getAmount());
        dto.setStatus(PaymentStatus.fromCode(payment.getStatus()).getDescription());
        dto.setPaymentTime(payment.getPaymentTime());
        dto.setDescription(payment.getDescription());
        dto.setPaymentMethod(payment.getPaymentMethod());
        return dto;
    }

    @Override
    public Payment toPayment(PaymentDto paymentDto) {
        if (paymentDto == null) {
            return null;
        }

        Payment payment = new Payment();
        payment.setId(paymentDto.getId());
        payment.setBooking(bookingRepository.findById(paymentDto.getBookingId()).orElse(null));
        payment.setTransactionId(paymentDto.getTransactionId());
        payment.setAmount(paymentDto.getAmount());
        payment.setStatus(PaymentStatus.fromDescription(paymentDto.getStatus()).getCode());
        payment.setPaymentTime(paymentDto.getPaymentTime());
        payment.setDescription(paymentDto.getDescription());
        payment.setPaymentMethod(paymentDto.getPaymentMethod());
        return payment;
    }
}
