package com.horizon.service.impl;

import com.horizon.domain.Booking;
import com.horizon.dto.BookingDto;
import com.horizon.mapper.BookingMapper;
import com.horizon.repository.BookingRepository;
import com.horizon.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Override
    public BookingDto create(BookingDto bookingDto) {
        Booking booking = bookingMapper.toBooking(bookingDto);
        return bookingMapper.toBookingDto(bookingRepository.save(booking));
    }

    @Override
    public BookingDto getById(Integer id) {
        return bookingMapper.toBookingDto(bookingRepository.getById(id));
    }

    @Override
    public List<BookingDto> getAll() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(bookingMapper::toBookingDto).toList();
    }





}
