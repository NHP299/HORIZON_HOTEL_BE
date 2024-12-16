package com.horizon.repository;

import com.horizon.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> getByAccountId(Integer accountId);

    Optional<Booking> findByPaymentId(Integer paymentId);

    List<Booking> findAllByStatusAndCheckOutBefore(Integer status, LocalDate date);

    @Query("SELECT b FROM Booking b " +
            "JOIN b.payment p " +
            "WHERE b.status = 1 " +
            "AND p.status = 3 ")
    List<Booking> findBookingByPaymentStatusFailed();
}
