package com.horizon.service.impl;

import com.horizon.domain.*;
import com.horizon.dto.BookingDto;
import com.horizon.dto.PaymentTransactionDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.BookingMapper;
import com.horizon.mapper.PaymentMapper;
import com.horizon.repository.*;
import com.horizon.service.BookingService;
import com.horizon.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
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

    private final PaymentMapper paymentMapper;

    @Override
    public BookingDto create(HttpServletRequest request, BookingDto bookingDto, String url) throws UnsupportedEncodingException {
        if (!checkBooking(bookingDto)) {
            throw new IllegalStateException("Booking failed.");
        }
        Booking booking = bookingMapper.toBooking(bookingDto);
        booking.setStatus(Booking.Status.PENDING);
        booking.setBookingDate(LocalDate.now());
        booking.setTotalPrice(bookingDto.getTotalPrice());
        bookingRepository.save(booking);
        for (int roomId : bookingDto.getRoomIds()) {
            Room room = roomRepository.findByIsActivatedTrueAndId(roomId)
                    .orElseThrow(() -> new IllegalStateException("Room " + roomId + " not found."));
            BookingDetail bookingDetail = new BookingDetail();
            bookingDetail.setBooking(booking);
            bookingDetail.setRoom(room);
            bookingDetail.setPriceAtBooking(room.getPrice());
            bookingDetailRepository.save(bookingDetail);
        }
        PaymentTransactionDto payment;
        if (request == null && url == null) {
            payment = paymentService.createCashPayment(booking);
        } else {
            payment = paymentService.create(url, booking);
        }

        booking.setPayment(paymentMapper.toPayment(payment));
        bookingRepository.save(booking);

        return bookingMapper.toBookingDto(booking);
    }

    @Override
    public BookingDto update(Integer id, BookingDto bookingDto) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
        if (booking.getPayment().getPaymentMethod().equalsIgnoreCase("cash")) {
            booking.setStatus(bookingDto.getStatus());
            return bookingMapper.toBookingDto(bookingRepository.save(booking));
        }
        return null;
    }

    public Boolean checkBooking(BookingDto bookingDto) {
        List<Integer> roomIds = bookingDto.getRoomIds();
        checkRoomAvailable(roomIds, bookingDto.getCheckIn(), bookingDto.getCheckOut());
        if (!checkValidCapacity(roomIds, bookingDto.getAdult(), bookingDto.getChild(), bookingDto.getBaby())) {
            throw new IllegalStateException("Total capacity of selected rooms is less than the required capacity.");
        }
        return true;
    }

    @Override
    public Boolean checkValidCapacity(List<Integer> roomIds, int adult, int child, int baby) {
        for (int roomId : roomIds) {
            int adultCapacity = roomRepository.findByIsActivatedTrueAndId(roomId)
                    .orElseThrow(() -> new IllegalStateException("Room " + roomId + " not found.")).getRoomType().getAdultCapacity();
            int childCapacity = roomRepository.findByIsActivatedTrueAndId(roomId)
                    .orElseThrow(() -> new IllegalStateException("Room " + roomId + " not found.")).getRoomType().getChildCapacity();
            int babyCapacity = roomRepository.findByIsActivatedTrueAndId(roomId)
                    .orElseThrow(() -> new IllegalStateException("Room " + roomId + " not found.")).getRoomType().getBabyCapacity();
            if (adultCapacity >= adult && childCapacity >= child && babyCapacity >= baby) {
                return true;
            }
        }
        return false;
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
    public Page<BookingDto> getAll(Pageable pageable) {
        Page<Booking> bookings = bookingRepository.findAll(pageable);
        return bookings.map(bookingMapper::toBookingDto);
    }

    @Override
    public Page<BookingDto> getByAccountId(Integer accountId, Pageable pageable) {
        Page<Booking> bookings = bookingRepository.getByAccountId(accountId, pageable);
        return bookings.map(bookingMapper::toBookingDto);
    }

    @Override
    public void confirmBooking(HttpServletRequest request) {
        Payment payment = paymentRepository.findByTransactionId(request.getParameter("vnp_TxnRef"));
        Booking booking = bookingRepository.findByPaymentId(payment.getId())
                .orElseThrow(() -> new IllegalStateException("Booking not found with payment id: " + request.getParameter("vnp_TxnRef")));
        booking.setStatus(Booking.Status.CONFIRMED);
        bookingRepository.save(booking);
    }

    @Scheduled(fixedRate = 60000)
    public void completeBooking() {
        LocalDate currentDate = LocalDate.now();

        List<Booking> bookings = bookingRepository.findAllByStatusAndCheckOutBefore(Booking.Status.CONFIRMED, currentDate);

        bookings.forEach(booking -> {
            booking.setStatus(Booking.Status.COMPLETED);
            bookingRepository.save(booking);
        });
        System.out.println("Updated expired bookings to Complete.");
    }

    @Scheduled(fixedRate = 60000)
    public void cancelExpiredBookings() {
        List<Booking> bookings = bookingRepository.findBookingByPaymentStatusFailed();

        bookings.forEach(booking -> {
            booking.setStatus(Booking.Status.CANCELLED);
            bookingRepository.save(booking);
        });

        System.out.println("Cancelled expired bookings.");
    }

}
