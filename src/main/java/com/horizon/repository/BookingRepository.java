package com.horizon.repository;

import com.horizon.domain.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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

    @Query(value = """
    SELECT 
        b.id AS bookingId,
        b.check_in AS checkIn,
        b.check_out AS checkOut,
        b.total_price AS totalPrice,
        b.booking_date AS bookingDate,
        b.adult AS adult,
        b.child AS child,
        b.baby AS baby,
        b.promotion_id AS promotion, 
        b.status AS status,
        b.note AS note,
        r.id AS roomId,
        r.name AS roomName,
        r.floor AS floor,
        r.description AS description,
        bd.price_at_booking AS priceAtBooking,
        p.payment_method AS paymentMethod
    FROM 
        booking b
    JOIN 
        booking_detail bd ON b.id = bd.booking_id
    JOIN 
        room r ON bd.room_id = r.id
    JOIN 
        payment p ON b.payment_id = p.id
    WHERE 
        b.account_id = :accountId
""", nativeQuery = true)
    Page<Map<String, Object>> findBookingsByAccountId(@Param("accountId") Integer accountId, Pageable pageable);

    @Query(value = """
    SELECT
            b.id AS bookingId,
            b.check_in AS checkIn,
            b.check_out AS checkOut,
            b.total_price AS totalPrice,
            b.booking_date AS bookingDate,
            b.adult AS adult,
            b.child AS child,
            b.baby AS baby,
            b.promotion_id AS promotionId,
            b.status AS status,
            b.note AS note,
            r.id AS roomId,
            r.name AS roomName,
            r.floor AS floor,
            r.description AS description,
            bd.price_at_booking AS priceAtBooking,
            p.transaction_id AS transactionId,
            p.payment_method AS paymentMethod,
            a.email AS email
        FROM   
            booking b
        JOIN
            booking_detail bd ON b.id = bd.booking_id
        JOIN
            room r ON bd.room_id = r.id
        JOIN
            payment p ON b.payment_id = p.id
        JOIN  
            account a ON b.account_id = a.id
        WHERE
            a.is_activated = true
            """, nativeQuery = true)
    Page<Map<String, Object>> getAll(Pageable pageable);
}
