package com.horizon.repository;

import com.horizon.domain.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Page<Booking> getByAccountId(Integer accountId, Pageable pageable);

    Optional<Booking> findByPaymentId(Integer paymentId);

    List<Booking> findAllByStatusAndCheckOutBefore(Booking.Status status, LocalDate checkOut);

    @Query("SELECT b FROM Booking b " +
            "JOIN b.payment p " +
            "WHERE b.status = com.horizon.domain.Booking.Status.PENDING " +
            "AND p.status = com.horizon.domain.Payment.Status.FAILED ")
    List<Booking> findBookingByPaymentStatusFailed();
}
