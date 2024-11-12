package com.horizon.service;


import com.horizon.dto.BookingDetailDto;

import java.util.List;

public interface BookingDetailService {
    BookingDetailDto create(BookingDetailDto bookingDetailDto);

    List<BookingDetailDto> getAll();

    List<BookingDetailDto> getAllByBookingId(Integer bookingId);
}
