package com.horizon.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.horizon.domain.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    Optional<Room> findByName(String name);

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

    Page<Room> findAvailableRoomsInDateRange(@Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable);

    @Query(value = "SELECT " +
            "r.id , " +
            "r.name , " +
            "r.status , " +
            "r.floor , " +
            "r.price , " +
            "r.description , " +
            "STRING_AGG(DISTINCT s.description, ', ') AS services, " +
            "STRING_AGG(DISTINCT u.name, ', ') AS utilities, " +
            "STRING_AGG(DISTINCT m.path, ', ') AS paths " +
            "FROM room r " +
            "LEFT JOIN room_type rt ON r.room_type_id = rt.id " +
            "LEFT JOIN services s ON rt.id = s.room_type_id " +
            "LEFT JOIN utilities u ON rt.id = u.room_type_id " +
            "LEFT JOIN media m ON rt.id = m.room_type_id " +
            "WHERE r.is_activated = true " +
            "GROUP BY r.id, r.name, r.status, r.floor, r.price, r.description", nativeQuery = true)
    List<Map<String, Object>> getRoomDetail();

}
