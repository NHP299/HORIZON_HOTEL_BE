package com.horizon.mapper.impl;

import com.horizon.domain.Payment;
import com.horizon.domain.status.PaymentStatus;
import com.horizon.dto.PaymentTransactionDto;
import com.horizon.mapper.PaymentMapper;
import com.horizon.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class PaymentMapperImpl implements PaymentMapper {

    private final BookingRepository bookingRepository;

    @Override
    public PaymentTransactionDto toPaymentDto(Payment payment) {
        if (payment == null) {
            return null;
        }

        PaymentTransactionDto dto = new PaymentTransactionDto();
        dto.setId(payment.getId());
        dto.setBookingId(payment.getBooking().getId());
        dto.setTransactionId(payment.getTransactionId());
        dto.setAmount(payment.getAmount());
        dto.setStatus(PaymentStatus.fromCode(payment.getStatus()).getDescription());
        dto.setPaymentTime(toTimeString(payment.getPaymentTime()));
        dto.setCreatedTime(toTimeString(payment.getCreatedTime()));
        dto.setExpiredTime(toTimeString(payment.getExpiredTime()));
        dto.setDescription(payment.getDescription());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setBankCode(payment.getBankCode());
        dto.setCardType(payment.getCardType());
        dto.setExtraData(payment.getExtraData());
        return dto;
    }

    @Override
    public Payment toPayment(PaymentTransactionDto paymentTransactionDto) {
        if (paymentTransactionDto == null) {
            return null;
        }

        Payment payment = new Payment();
        payment.setId(paymentTransactionDto.getId());
        payment.setBooking(bookingRepository.findById(paymentTransactionDto.getBookingId()).orElse(null));
        payment.setTransactionId(paymentTransactionDto.getTransactionId());
        payment.setAmount(paymentTransactionDto.getAmount());
        payment.setStatus(PaymentStatus.fromDescription(paymentTransactionDto.getStatus()).getCode());
        payment.setPaymentTime(toTimestamp(paymentTransactionDto.getPaymentTime()));
        payment.setCreatedTime(toTimestamp(paymentTransactionDto.getCreatedTime()));
        payment.setExpiredTime(toTimestamp(paymentTransactionDto.getExpiredTime()));
        payment.setDescription(paymentTransactionDto.getDescription());
        payment.setPaymentMethod(paymentTransactionDto.getPaymentMethod());
        payment.setBankCode(paymentTransactionDto.getBankCode());
        payment.setCardType(paymentTransactionDto.getCardType());
        payment.setExtraData(paymentTransactionDto.getExtraData());

        return payment;
    }

    @Override
    public Timestamp toTimestamp(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime localDateTime = LocalDateTime.parse(time, formatter);
        return Timestamp.valueOf(localDateTime);
    }

    @Override
    public String toTimeString(Timestamp timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        return localDateTime.format(formatter);
    }
}
