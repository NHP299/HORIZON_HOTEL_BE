package com.horizon.service.impl;

import com.horizon.domain.*;
import com.horizon.dto.BookingDto;
import com.horizon.dto.RoomDto;
import com.horizon.mapper.BookingMapper;
import com.horizon.mapper.RoomMapper;
import com.horizon.repository.*;
import com.horizon.service.BookingService;
import com.horizon.service.PromotionService;
import com.horizon.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingMapper bookingMapper;

    private final RoomMapper roomMapper;

    private final BookingRepository bookingRepository;

    private final BookingDetailRepository bookingDetailRepository;

    private final PaymentRepository paymentRepository;

    private final RoomRepository roomRepository;

    private final PromotionRepository promotionRepository;
    private final AccountRepository accountRepository;
    private final PromotionTypeRepository promotionTypeRepository;

    public BookingDto create( int accountId,
                              List<Integer> roomIds,
                              LocalDate checkIn,
                              LocalDate checkOut,
                              int adultCount,
                              int childCount,
                              int babyCount,
                              int promotionId,
                              int paymentId) {
        for (int roomId : roomIds) {
            Room room = Optional.ofNullable(roomRepository.findByIsActivatedTrueAndId(roomId))
                    .orElseThrow(() -> new IllegalStateException("Room " + roomId + " not found."));
            if (!isRoomAvailable(roomId, checkIn, checkOut)) {
                throw new IllegalStateException("Room " + room.getName() + " is not available for the selected dates.");
            }
        }

        Double totalPrice = 0.0;
        for (int roomId : roomIds) {
            Room room = roomRepository.findByIsActivatedTrueAndId(roomId);
            totalPrice += room.getPrice();
        }

        double discount = 0;
        if (promotionId != 0) {
            Promotion promotion = promotionRepository.findByIdAndAvailable(promotionId)
                    .orElseThrow(() -> new IllegalArgumentException("Promotion with ID " + promotionId + " not found."));
            if (totalPrice >= promotion.getMaxAmount()) {
                if (promotionTypeRepository.findByPromotionId(promotionId).stream().anyMatch(pt -> pt.getName().equalsIgnoreCase("percent"))) {
                    discount = totalPrice * promotion.getMaxAmount() / 100.0;
                } else if (promotionTypeRepository.findByPromotionId(promotionId).stream().anyMatch(pt -> pt.getName().equalsIgnoreCase("fixed"))) {
                    discount = promotion.getMaxAmount();
                }
                totalPrice -= discount;
            }
        }

        Booking booking = new Booking();
        booking.setAccount(accountRepository.getById(accountId));
        booking.setCheckIn(checkIn);
        booking.setCheckOut(checkOut);
        booking.setBookingDate(LocalDate.now());
        booking.setAdult(adultCount);
        booking.setChild(childCount);
        booking.setBaby(babyCount);
        booking.setStatus(1);
        booking.setTotalPrice(totalPrice);
        booking.setPromotion(promotionRepository.getById(promotionId));
        bookingRepository.save(booking);

        for (int roomId : roomIds) {
            BookingDetail bookingDetail = new BookingDetail();
            bookingDetail.setBooking(booking);
            bookingDetail.setRoom(roomRepository.getById(roomId));
            bookingDetailRepository.save(bookingDetail);
        }

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setTransactionId("TX-" + System.currentTimeMillis());
        payment.setAmount(totalPrice);
        payment.setStatus(1);
        payment.setPaymentTime(Timestamp.valueOf(LocalDateTime.now()));
        payment.setDescription("Payment for booking ID " + booking.getId());
        payment.setPaymentMethod("E-Wallet");
        paymentRepository.save(payment);

        return bookingMapper.toBookingDto(booking);
    }


    private boolean isRoomAvailable(int roomId, LocalDate checkIn, LocalDate checkOut) {
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
