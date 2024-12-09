package com.horizon.mapper;

import com.horizon.domain.Booking;
import com.horizon.dto.BookingDto;

public interface BookingMapper {

    BookingDto toBookingDto(Booking booking);

    Booking toBooking(BookingDto bookingDto);
}
