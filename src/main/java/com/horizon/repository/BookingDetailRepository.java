package com.horizon.repository;

import com.horizon.domain.BookingDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingDetailRepository extends JpaRepository<BookingDetail, Integer> {

    Page<BookingDetail> findByBookingId(Integer bookingId, Pageable pageable);

    @Query("SELECT bd FROM BookingDetail bd " +
            "JOIN Booking b ON bd.booking.id = b.id " +
            "WHERE bd.room.id = :roomId AND" +
            "(b.checkIn <= :checkOut AND b.checkOut >= :checkIn)" +
            "AND b.status <> com.horizon.domain.Booking.Status.CANCELLED ")
    List<BookingDetail> findConflictingBookings(@Param("roomId") int roomId,
                                                @Param("checkIn") LocalDate checkIn,
                                                @Param("checkOut") LocalDate checkOut);

    @Query("SELECT bd FROM BookingDetail bd " +
            "JOIN Booking b ON bd.booking.id = b.id " +
            "WHERE bd.room.id = :roomId AND " +
            "(b.checkIn <= :date AND b.checkOut > :date)"+
            "AND b.status <> com.horizon.domain.Booking.Status.CANCELLED ")
    List<BookingDetail> findBookingDetailOccupied(@Param("roomId") int roomId,
                                                  @Param("date") LocalDate date);

    @Query("SELECT bd FROM BookingDetail bd " +
            "JOIN Booking b ON bd.booking.id = b.id " +
            "WHERE bd.room.id = :roomId AND " +
            "(b.bookingDate <= :date AND b.checkIn > :date)" +
            "AND b.status <> com.horizon.domain.Booking.Status.CANCELLED ")
    List<BookingDetail> findBookingDetailReserved(@Param("roomId") int roomId,
                                                  @Param("date") LocalDate date);

}
