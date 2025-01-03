package com.horizon.service;


import com.horizon.domain.Booking;
import com.horizon.dto.BookingDetailDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookingDetailService {
    BookingDetailDto create(BookingDetailDto bookingDetailDto);

    Page<BookingDetailDto> getAll(Pageable pageable);

    Page<BookingDetailDto> getAllByBookingId(Integer bookingId, Pageable pageable);

}
