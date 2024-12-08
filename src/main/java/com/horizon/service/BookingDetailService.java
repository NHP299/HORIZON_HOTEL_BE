package com.horizon.service;


import com.horizon.domain.Booking;
import com.horizon.dto.BookingDetailDto;

import java.util.List;

public interface BookingDetailService {
    BookingDetailDto create(BookingDetailDto bookingDetailDto);

    List<BookingDetailDto> getAll();

    List<BookingDetailDto> getAllByBookingId(Integer bookingId);

    List<BookingDetailDto> createByBooking(List<Integer> roomIds, Booking booking);

    BookingDetailDto create(Integer bookingId, Integer roomId);
}
