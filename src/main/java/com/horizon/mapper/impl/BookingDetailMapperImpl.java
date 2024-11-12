package com.horizon.mapper.impl;

import com.horizon.domain.Booking;
import com.horizon.domain.BookingDetail;
import com.horizon.domain.Room;
import com.horizon.dto.BookingDetailDto;
import com.horizon.mapper.BookingDetailMapper;
import org.springframework.stereotype.Service;

@Service
public class BookingDetailMapperImpl implements BookingDetailMapper {
    @Override
    public BookingDetailDto toBookingDetailDto(BookingDetail bookingDetail) {
        if (bookingDetail == null) {
            return null;
        }

        BookingDetailDto dto = new BookingDetailDto();
        dto.setId(bookingDetail.getId());
        dto.setBookingId(bookingDetail.getBooking() != null ? bookingDetail.getBooking().getId() : null);
        dto.setRoomId(bookingDetail.getRoom() != null ? bookingDetail.getRoom().getId() : null);

        return dto;
    }

    @Override
    public BookingDetail toBookingDetail(BookingDetailDto dto) {
        if (dto == null) {
            return null;
        }

        BookingDetail bookingDetail = new BookingDetail();
        bookingDetail.setId(dto.getId());

        if (dto.getBookingId() != null) {
            Booking booking = new Booking();
            booking.setId(dto.getBookingId());
            bookingDetail.setBooking(booking);
        }

        if (dto.getRoomId() != null) {
            Room room = new Room();
            room.setId(dto.getRoomId());
            bookingDetail.setRoom(room);
        }

        return bookingDetail;
    }
}
