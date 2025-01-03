package com.horizon.service;


import com.horizon.domain.Booking;
import com.horizon.dto.BookingDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    BookingDto create(HttpServletRequest request,BookingDto bookingDto, String url) throws UnsupportedEncodingException;

    BookingDto update(Integer id, BookingDto bookingDto);

    Boolean checkValidCapacity(List<Integer> roomIds, int adult,int child,int baby);

    void checkRoomAvailable(List<Integer> roomIds, LocalDate checkIn, LocalDate checkOut);

    boolean isRoomAvailable(int roomId, LocalDate checkIn, LocalDate checkOut);

    BookingDto getById(Integer id);

    Page<BookingDto> getAll(Pageable pageable);

    Page<BookingDto> getByAccountId(Integer accountId, Pageable pageable);

    void confirmBooking(HttpServletRequest request);

    @Scheduled(fixedRate = 3600000)
    void completeBooking();

    @Scheduled(fixedRate = 60000)
    void cancelExpiredBookings();
}
