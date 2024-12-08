package com.horizon.service;


import com.horizon.domain.Booking;
import com.horizon.dto.BookingDto;
import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    BookingDto create(HttpServletRequest request,BookingDto bookingDto, String url) throws UnsupportedEncodingException;

    void checkRoomAvailable(List<Integer> roomIds, LocalDate checkIn, LocalDate checkOut);

    boolean isRoomAvailable(int roomId, LocalDate checkIn, LocalDate checkOut);

    BookingDto getById(Integer id);

    List<BookingDto> getAll();

    List<BookingDto> getByAccountId(Integer accountId);
}
