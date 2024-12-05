package com.horizon.mapper.impl;

import com.horizon.domain.*;
import com.horizon.domain.status.BookingStatus;
import com.horizon.dto.BookingDto;
import com.horizon.mapper.BookingMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class BookingMapperImpl implements BookingMapper {
    @Override
    public BookingDto toBookingDto(Booking booking) {
        if (booking == null) {
            return null;
        }

        BookingDto dto = new BookingDto();
        dto.setId(booking.getId());
        dto.setAccountId(booking.getAccount() != null ? booking.getAccount().getId() : null);
        dto.setCheckIn(booking.getCheckIn() != null ? java.sql.Date.valueOf(booking.getCheckIn()) : null);
        dto.setCheckOut(booking.getCheckOut() != null ? java.sql.Date.valueOf(booking.getCheckOut()) : null);
        dto.setBookingDate(booking.getBookingDate() != null ? java.sql.Date.valueOf(booking.getBookingDate()) : null);
        dto.setAdult(booking.getAdult());
        dto.setChild(booking.getChild());
        dto.setBaby(booking.getBaby());
        dto.setStatus(BookingStatus.fromCode(booking.getStatus()).getDescription());
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

        booking.setCheckIn(dto.getCheckIn() != null ? LocalDate.parse(dto.getCheckIn().toLocaleString()) : null);
        booking.setCheckOut(dto.getCheckOut() != null ? LocalDate.parse(dto.getCheckOut().toLocaleString()) : null);
        booking.setBookingDate(dto.getBookingDate() != null ? LocalDate.parse(dto.getBookingDate().toLocaleString()) : null);
        booking.setAdult(dto.getAdult());
        booking.setChild(dto.getChild());
        booking.setBaby(dto.getBaby());
        booking.setStatus(BookingStatus.fromDescription(dto.getStatus()));
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
