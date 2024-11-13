package com.horizon.repository;

import com.horizon.domain.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    Room findByIsActivatedTrueAndId(Integer id);

    List<Room> findByIsActivatedTrue();

    List<Room> findByNameContainingIgnoreCaseAndIsActivatedTrue(String keywords);

    @Query("SELECT r FROM Room r WHERE LOWER(r.roomType.name) LIKE LOWER(CONCAT('%', :roomTypeName, '%'))")
    List<Room> findByRoomTypeName(@Param("roomTypeName") String roomTypeName);

    List<Room> findByStatusAndIsActivatedTrue(Integer status);

    @Query("SELECT r FROM Room r " +
            "LEFT JOIN BookingDetail bd ON r.id = bd.room.id " +
            "LEFT JOIN Booking b ON bd.booking.id = b.id " +
            "WHERE r.isActivated = true " +
            "AND r.status = 0 " +
            "AND (b.id IS NULL " +
            "OR b.checkIn NOT BETWEEN :startDate AND :endDate " +
            "OR b.checkOut NOT BETWEEN :startDate AND :endDate)")
    List<Room> findAvailableRoomsInDateRange(@Param("startDate") LocalDate startDate,
                                             @Param("endDate") LocalDate endDate);
}
