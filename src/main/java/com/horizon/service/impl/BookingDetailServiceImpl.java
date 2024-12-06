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
    public List<BookingDetailDto> getAll() {
        List<BookingDetail> bookingDetails = bookingDetailRepository.findAll();
        return bookingDetails.stream().map(bookingDetailMapper::toBookingDetailDto).toList();
    }

    @Override
    public List<BookingDetailDto> getAllByBookingId(Integer bookingId) {
        List<BookingDetail> bookingDetails = bookingDetailRepository.findByBookingId(bookingId);
        return bookingDetails.stream().map(bookingDetailMapper::toBookingDetailDto).toList();
    }

    @Override
    public List<BookingDetailDto> createByBooking(List<Integer> roomIds, Booking booking){
        for (int roomId : roomIds) {
            BookingDetail bookingDetail = new BookingDetail();
            bookingDetail.setBooking(booking);
            bookingDetail.setRoom(roomRepository.getById(roomId));
            bookingDetailRepository.save(bookingDetail);
        }
        return getAllByBookingId(booking.getId());
    }
}
