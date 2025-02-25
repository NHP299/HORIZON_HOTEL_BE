package com.horizon.service.impl;

import com.horizon.domain.Booking;
import com.horizon.domain.BookingDetail;
import com.horizon.dto.BookingDetailDto;
import com.horizon.dto.BookingDto;
import com.horizon.mapper.BookingDetailMapper;
import com.horizon.mapper.BookingMapper;
import com.horizon.repository.BookingDetailRepository;
import com.horizon.repository.RoomRepository;
import com.horizon.service.BookingDetailService;
import com.horizon.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingDetailServiceImpl implements BookingDetailService {
    private final BookingDetailRepository bookingDetailRepository;
    private final BookingDetailMapper bookingDetailMapper;
    @Lazy
    private final BookingService bookingService;
    private final BookingMapper bookingMapper;
    private final RoomRepository roomRepository;

    @Override
    public BookingDetailDto create(BookingDetailDto bookingDetailDto) {
        BookingDetail bookingDetail = bookingDetailMapper.toBookingDetail(bookingDetailDto);
        BookingDto bookingDto = bookingService.getById(bookingDetailDto.getBookingId());
        bookingDetail.setBooking(bookingMapper.toBooking(bookingDto));
        return bookingDetailMapper.toBookingDetailDto(bookingDetailRepository.save(bookingDetail));
    }

    @Override
    public Page<BookingDetailDto> getAll(Pageable pageable) {
        Page<BookingDetail> bookingDetails = bookingDetailRepository.findAll(pageable);
        return bookingDetails.map(bookingDetailMapper::toBookingDetailDto);
    }

    @Override
    public Page<BookingDetailDto> getAllByBookingId(Integer bookingId, Pageable pageable) {
        Page<BookingDetail> bookingDetails = bookingDetailRepository.findByBookingId(bookingId, pageable);
        return bookingDetails.map(bookingDetailMapper::toBookingDetailDto);
    }

}
