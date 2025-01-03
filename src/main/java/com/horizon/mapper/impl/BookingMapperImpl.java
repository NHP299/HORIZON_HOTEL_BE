package com.horizon.mapper.impl;

import com.horizon.domain.*;
import com.horizon.dto.BookingDto;
import com.horizon.mapper.BookingMapper;
import org.springframework.stereotype.Service;


@Service
public class BookingMapperImpl implements BookingMapper {
    @Override
    public BookingDto toBookingDto(Booking booking) {
        if (booking == null) {
            return null;
        }

        BookingDto dto = new BookingDto();
        dto.setId(booking.getId());
        dto.setPaymentId(booking.getPayment() != null ? booking.getPayment().getId() : null);
        dto.setAccountId(booking.getAccount() != null ? booking.getAccount().getId() : null);
        dto.setCheckIn(booking.getCheckIn() != null ? booking.getCheckIn() : null);
        dto.setCheckOut(booking.getCheckOut() != null ? booking.getCheckOut() : null);
        dto.setBookingDate(booking.getBookingDate() != null ? booking.getBookingDate() : null);
        dto.setAdult(booking.getAdult());
        dto.setChild(booking.getChild());
        dto.setBaby(booking.getBaby());
        dto.setStatus(booking.getStatus() != null ? booking.getStatus() : null);
        dto.setNote(booking.getNote());
        dto.setTotalPrice(booking.getTotalPrice() != null ? booking.getTotalPrice() : null);
        dto.setPromotionId(booking.getPromotion() != null ? booking.getPromotion().getId() : null);

        return dto;
    }

    @Override
    public Booking toBooking(BookingDto dto) {
        if (dto == null) {
            return null;
        }

        Booking booking = new Booking();
        booking.setId(dto.getId());

        if (dto.getAccountId() != null) {
            Account account = new Account();
            account.setId(dto.getAccountId());
            booking.setAccount(account);
        }

        if (dto.getPaymentId() != null) {
            Payment payment = new Payment();
            payment.setId(dto.getPaymentId());
            booking.setPayment(payment);
        }

        booking.setCheckIn(dto.getCheckIn() != null ? dto.getCheckIn(): null);
        booking.setCheckOut(dto.getCheckOut() != null ? dto.getCheckOut() : null);
        booking.setBookingDate(dto.getBookingDate() != null ? dto.getBookingDate() : null);
        booking.setAdult(dto.getAdult());
        booking.setChild(dto.getChild());
        booking.setBaby(dto.getBaby());
        booking.setStatus(dto.getStatus() != null ? dto.getStatus() : null);
        booking.setNote(dto.getNote());
        booking.setTotalPrice(dto.getTotalPrice() != null ? dto.getTotalPrice() : null);

        if (dto.getPromotionId() != null) {
            Promotion promotion = new Promotion();
            promotion.setId(dto.getPromotionId());
            booking.setPromotion(promotion);
        }

        return booking;
    }
}
