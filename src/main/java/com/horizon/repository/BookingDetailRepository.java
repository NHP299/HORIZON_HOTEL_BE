package com.horizon.repository;

import com.horizon.domain.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingDetailRepository extends JpaRepository<BookingDetail, Integer> {

    List<BookingDetail> findByBookingId(Integer bookingId);

    @Query("SELECT bd FROM BookingDetail bd " +
            "JOIN Booking b ON bd.booking.id = b.id " +
            "WHERE bd.room.id = :roomId AND" +
            "(b.checkIn < :checkOut AND b.checkOut > :checkIn)")
    List<BookingDetail> findConflictingBookings(@Param("roomId") int roomId,
                                                @Param("checkIn") LocalDate checkIn,
                                                @Param("checkOut") LocalDate checkOut);
}
