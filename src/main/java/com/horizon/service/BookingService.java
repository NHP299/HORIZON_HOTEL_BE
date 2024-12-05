package com.horizon.service;


import com.horizon.domain.Booking;
import com.horizon.dto.BookingDto;

import java.util.List;

public interface BookingService {

    BookingDto getById(Integer id);

    List<BookingDto> getAll();

    List<BookingDto> getByAccountId(Integer accountId);
}
