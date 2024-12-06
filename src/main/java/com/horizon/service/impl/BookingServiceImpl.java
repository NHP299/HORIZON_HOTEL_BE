package com.horizon.service.impl;

import com.horizon.domain.*;
import com.horizon.dto.BookingDto;
import com.horizon.mapper.BookingMapper;
import com.horizon.mapper.PaymentMapper;
import com.horizon.repository.*;
import com.horizon.service.BookingDetailService;
import com.horizon.service.BookingService;
import com.horizon.service.PaymentService;
import com.horizon.service.PromotionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final PaymentMapper paymentMapper;

    private final BookingMapper bookingMapper;

    private final BookingRepository bookingRepository;

    private final BookingDetailRepository bookingDetailRepository;

    private final PaymentRepository paymentRepository;

    private final RoomRepository roomRepository;

    private final PromotionRepository promotionRepository;

    private final AccountRepository accountRepository;

    private final PromotionTypeRepository promotionTypeRepository;

    private final PromotionService promotionService;

    private final PaymentService paymentService;

    @Override
    public BookingDto create(HttpServletRequest request, String url) throws UnsupportedEncodingException {

        List<Integer> roomIds = List.of(Integer.parseInt(request.getParameter("roomId")));
        LocalDate checkIn = LocalDate.parse(request.getParameter("checkIn"));
        LocalDate checkOut = LocalDate.parse(request.getParameter("checkOut"));

        checkRoomAvailable(roomIds, checkIn, checkOut);
        Booking booking = new Booking();
        booking.setAccount(accountRepository.getById(Integer.parseInt(request.getParameter("accountId"))));
        booking.setCheckIn(checkIn);
        booking.setCheckOut(checkOut);
        booking.setBookingDate(LocalDate.now());
        booking.setAdult(Integer.parseInt(request.getParameter("adultCount")));
        booking.setChild(Integer.parseInt(request.getParameter("childCount")));
        booking.setBaby(Integer.parseInt(request.getParameter("babyCount")));
        booking.setStatus(1);
        booking.setTotalPrice(Double.parseDouble(request.getParameter("totalPrice")));
        booking.setPromotion(promotionRepository.getById(Integer.parseInt(request.getParameter("promotionId"))));

        for (int roomId : roomIds) {
            BookingDetail bookingDetail = new BookingDetail();
            bookingDetail.setBooking(booking);
            bookingDetail.setRoom(roomRepository.getById(roomId));
            bookingDetailRepository.save(bookingDetail);
        }

        Payment payment = paymentService.create(url, booking);

        booking.setPayment(payment);
        bookingRepository.save(booking);

        return bookingMapper.toBookingDto(booking);
    }


    @Override
    public void checkRoomAvailable(List<Integer> roomIds, LocalDate checkIn, LocalDate checkOut) {
        for (int roomId : roomIds) {
            Room room = Optional.ofNullable(roomRepository.findByIsActivatedTrueAndId(roomId))
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

}
