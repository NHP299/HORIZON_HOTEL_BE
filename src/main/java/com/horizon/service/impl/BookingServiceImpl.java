package com.horizon.service.impl;

import com.horizon.domain.*;
import com.horizon.dto.BookingDto;
import com.horizon.mapper.BookingMapper;
import com.horizon.mapper.PaymentMapper;
import com.horizon.mapper.PromotionMapper;
import com.horizon.repository.*;
import com.horizon.service.BookingService;
import com.horizon.service.PaymentService;
import com.horizon.service.PromotionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;


@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingMapper bookingMapper;

    private final BookingRepository bookingRepository;

    private final BookingDetailRepository bookingDetailRepository;

    private final PaymentRepository paymentRepository;

    private final RoomRepository roomRepository;

    private final PaymentService paymentService;

    @Override
    public BookingDto create(HttpServletRequest request,BookingDto bookingDto, String url) throws UnsupportedEncodingException {
        List<Integer> roomIds = bookingDto.getRoomIds();
        checkRoomAvailable(roomIds, bookingDto.getCheckIn(), bookingDto.getCheckOut());
        int totalPeople = bookingDto.getAdult() + bookingDto.getChild() + bookingDto.getBaby();
        if (!checkValidCapacity(roomIds, totalPeople)) {
            throw new IllegalStateException("Total capacity of selected rooms is less than the required capacity.");
        }
        Booking booking = bookingMapper.toBooking(bookingDto);
        booking.setTotalPrice(Double.valueOf(request.getParameter("amount")));
        bookingRepository.save(booking);

        for (int roomId : roomIds) {
            BookingDetail bookingDetail = new BookingDetail();
            bookingDetail.setBooking(booking);
            bookingDetail.setRoom(roomRepository.findByIsActivatedTrueAndId(roomId)
                    .orElseThrow(() -> new IllegalStateException("Room " + roomId + " not found.")));
            bookingDetailRepository.save(bookingDetail);
        }

        Payment payment = paymentService.create(url, booking);

        booking.setPayment(payment);
        bookingRepository.save(booking);

        return bookingMapper.toBookingDto(booking);
    }

    @Override
    public Boolean checkValidCapacity(List<Integer> roomIds, Integer capacity) {
        int totalCapacity = 0;
        for (int roomId : roomIds) {
          int i = roomRepository.findByIsActivatedTrueAndId(roomId)
              .orElseThrow(() -> new IllegalStateException("Room " + roomId + " not found.")).getRoomType().getCapacity();
            totalCapacity += i;
        }
        return totalCapacity >= capacity;
    }


    @Override
    public void checkRoomAvailable(List<Integer> roomIds, LocalDate checkIn, LocalDate checkOut) {
        for (int roomId : roomIds) {
            Room room = roomRepository.findByIsActivatedTrueAndId(roomId)
                    .orElseThrow(() -> new IllegalStateException("Room " + roomId + " not found."));
            if (!isRoomAvailable(roomId, checkIn, checkOut)) {
                throw new IllegalStateException("Room " + room.getName() + " is not available for the selected dates.");
            }
        }
    }

    @Override
    public boolean isRoomAvailable(int roomId, LocalDate checkIn, LocalDate checkOut) {
        List<BookingDetail> conflictingBookings = bookingDetailRepository.findConflictingBookings(roomId, checkIn, checkOut);
        return conflictingBookings.isEmpty();
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

    @Override
    public List<BookingDto> getByAccountId(Integer accountId) {
        List<Booking> bookings = bookingRepository.getByAccountId(accountId);
        return bookings.stream().map(bookingMapper::toBookingDto).toList();
    }

    @Override
    public void confirmBooking(HttpServletRequest request) {
        Payment payment = paymentRepository.findByTransactionId(request.getParameter("vnp_TxnRef"));
        Booking booking = bookingRepository.findByPaymentId(payment.getId())
                .orElseThrow(() -> new IllegalStateException("Booking not found with payment id: " + request.getParameter("vnp_TxnRef")));
        booking.setStatus(2);
        bookingRepository.save(booking);
    }

}
