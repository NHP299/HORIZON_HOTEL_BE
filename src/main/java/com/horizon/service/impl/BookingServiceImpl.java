package com.horizon.service.impl;

import com.horizon.domain.*;
import com.horizon.dto.BookingDto;
import com.horizon.mapper.BookingMapper;
import com.horizon.repository.*;
import com.horizon.service.BookingService;
import com.horizon.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
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

    @Override
    public BookingDto create(HttpServletRequest request, BookingDto bookingDto, String url) throws UnsupportedEncodingException {
        if (!checkBooking(bookingDto)) {
            throw new IllegalStateException("Booking failed.");
        }
        Booking booking = bookingMapper.toBooking(bookingDto);
        booking.setBookingDate(LocalDate.now());
        booking.setTotalPrice(bookingDto.getTotalPrice());
        bookingRepository.save(booking);
        for (int roomId : bookingDto.getRoomIds()) {
            BookingDetail bookingDetail = new BookingDetail();
            bookingDetail.setBooking(booking);
            bookingDetail.setRoom(roomRepository.findByIsActivatedTrueAndId(roomId)
                    .orElseThrow(() -> new IllegalStateException("Room " + roomId + " not found.")));
            bookingDetailRepository.save(bookingDetail);
        }
        Payment payment;
        if (request == null && url == null) {
            payment = paymentService.createCashPayment(booking);
        }else{
            payment = paymentService.create(url, booking);
        }

        booking.setPayment(payment);
        bookingRepository.save(booking);

        return bookingMapper.toBookingDto(booking);
    }

    public Boolean checkBooking(BookingDto bookingDto) {
        List<Integer> roomIds = bookingDto.getRoomIds();
        checkRoomAvailable(roomIds, bookingDto.getCheckIn(), bookingDto.getCheckOut());
        int totalPeople = bookingDto.getAdult() + bookingDto.getChild() + bookingDto.getBaby();
        if (!checkValidCapacity(roomIds, totalPeople)) {
            throw new IllegalStateException("Total capacity of selected rooms is less than the required capacity.");
        }
        return true;
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
        booking.setStatus(Booking.Status.CONFIRMED);
        bookingRepository.save(booking);
    }

    @Override
    @Scheduled(fixedRate = 60000)
    public void completeBooking() {
        LocalDate currentDate = LocalDate.now();

        List<Booking> bookings = bookingRepository.findAllByStatusAndCheckOutBefore(Booking.Status.CONFIRMED, currentDate);

        List<Payment> payment = bookings.stream().map(Booking::getPayment)
                .filter(p -> p.getPaymentMethod() == null || p.getPaymentMethod().equalsIgnoreCase("pay"))
                .toList();

        List<Booking> expiredBookings = payment.stream().map(Payment::getBooking)
                .toList();

        expiredBookings.forEach(booking -> {
            booking.setStatus(Booking.Status.COMPLETED);
            bookingRepository.save(booking);
        });
        System.out.println("Updated expired bookings to Complete.");
    }

    @Override
    @Scheduled(fixedRate = 60000)
    public void cancelExpiredBookings() {
        List<Booking> bookings = bookingRepository.findBookingByPaymentStatusFailed();

        List<Payment> payment = bookings.stream().map(Booking::getPayment)
                .filter(p -> p.getPaymentMethod() == null || p.getPaymentMethod().equalsIgnoreCase("pay"))
                .toList();

        List<Booking> expiredBookings = payment.stream().map(Payment::getBooking)
                .toList();

        expiredBookings.forEach(booking -> {
            booking.setStatus(Booking.Status.CANCELLED);
            bookingRepository.save(booking);
        });

        System.out.println("Cancelled expired bookings.");
    }

}
