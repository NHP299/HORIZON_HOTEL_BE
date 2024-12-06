package com.horizon.service;


import com.horizon.domain.Booking;
import com.horizon.dto.BookingDto;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    void checkRoomAvailable(List<Integer> roomIds, LocalDate checkIn, LocalDate checkOut);

    boolean isRoomAvailable(int roomId, LocalDate checkIn, LocalDate checkOut);

    BookingDto getById(Integer id);

    List<BookingDto> getAll();

    List<BookingDto> getByAccountId(Integer accountId);
}
