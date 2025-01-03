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

    Optional<Room> findByIsActivatedTrueAndId(Integer id);

    Page<Room> findByIsActivatedTrue(Pageable pageable);

    List<Room> findByNameContainingIgnoreCaseAndIsActivatedTrue(String keywords);

    @Query("SELECT r FROM Room r WHERE LOWER(r.roomType.name)" +
            " LIKE LOWER(CONCAT('%', :roomTypeName, '%'))")
    Page<Room> findByRoomTypeName(@Param("roomTypeName")
                                  String roomTypeName, Pageable pageable);

    Page<Room> findByStatusAndIsActivatedTrue(Room.Status status, Pageable pageable);

    @Query("SELECT r FROM Room r " +
            "LEFT JOIN BookingDetail bd ON r.id = bd.room.id " +
            "LEFT JOIN Booking b ON bd.booking.id = b.id " +
            "WHERE r.isActivated = true " +
            "AND r.status = com.horizon.domain.Room.Status.AVAILABLE " +
            "AND (b.id IS NULL " +
            "OR b.checkIn NOT BETWEEN :startDate AND :endDate " +
            "OR b.checkOut NOT BETWEEN :startDate AND :endDate)")
    Page<Room> findAvailableRoomsInDateRange(@Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate, Pageable pageable);

    @Query(value = """
        SELECT
            r.id,
            r.name,
            r.status,
            r.floor,
            r.price,
            r.description,
            rt.adult_capacity , 
            rt.child_capacity , 
            rt.baby_capacity , 
            STRING_AGG(DISTINCT s.name, ', ') AS services,
            STRING_AGG(DISTINCT u.name, ', ') AS utilities,
            STRING_AGG(DISTINCT m.path, ', ') AS paths
        FROM
            room r
        LEFT JOIN
            room_type rt ON r.room_type_id = rt.id
        LEFT JOIN
            room_type_services rts ON rt.id = rts.room_type_id AND rts.is_activated = true
        LEFT JOIN
            services s ON rts.service_id = s.id AND s.is_activated = true
        LEFT JOIN
            room_type_utilities rtu ON rt.id = rtu.room_type_id AND rtu.is_activated = true
        LEFT JOIN
            utilities u ON rtu.utility_id = u.id AND u.is_activated = true
        LEFT JOIN
            media m ON rt.id = m.room_type_id
        WHERE
            r.is_activated = true
        GROUP BY
            r.id, r.name, r.status, r.floor, r.price, r.description, rt.adult_capacity ,rt.child_capacity ,rt.baby_capacity
        """, nativeQuery = true)
    Page<Map<String, Object>> getRoomDetail(Pageable pageable);

    @Query(value = """
        SELECT
            r.id,
            r.name,
            r.status,
            r.floor,
            r.price,
            r.description,
            rt.adult_capacity , 
            rt.child_capacity , 
            rt.baby_capacity , 
            STRING_AGG(DISTINCT s.name, ', ') AS services,
            STRING_AGG(DISTINCT u.name, ', ') AS utilities,
            STRING_AGG(DISTINCT m.path, ', ') AS paths
        FROM
            room r
        LEFT JOIN
            room_type rt ON r.room_type_id = rt.id
        LEFT JOIN
            room_type_services rts ON rt.id = rts.room_type_id AND rts.is_activated = true
        LEFT JOIN
            services s ON rts.service_id = s.id AND s.is_activated = true
        LEFT JOIN
            room_type_utilities rtu ON rt.id = rtu.room_type_id AND rtu.is_activated = true
        LEFT JOIN
            utilities u ON rtu.utility_id = u.id AND u.is_activated = true
        LEFT JOIN
            media m ON rt.id = m.room_type_id
        WHERE
            r.is_activated = true AND r.id =:id
        GROUP BY
            r.id, r.name, r.status, r.floor, r.price, r.description, rt.adult_capacity ,rt.child_capacity ,rt.baby_capacity
        """, nativeQuery = true)
    Map<String, Object> getRoomDetailById(@Param("id") Integer id);

    @Query("""
        SELECT r 
        FROM Room r 
        JOIN r.roomType rt 
        WHERE (:roomTypeName IS NULL OR LOWER(rt.name) LIKE LOWER(CONCAT('%', :roomTypeName, '%'))) 
          AND rt.adultCapacity >= :adultCapacity
          AND rt.childCapacity >= :childCapacity
          AND rt.babyCapacity >= :babyCapacity
          AND r.isActivated = true
          AND r.status <> 'MAINTENANCE'
          AND r.id NOT IN (
              SELECT bd.room.id 
              FROM BookingDetail bd 
              JOIN bd.booking b 
              WHERE b.checkIn < :checkOutDate 
                AND b.checkOut > :checkInDate
                AND b.status <> com.horizon.domain.Booking.Status.CANCELLED
          )
    """)
    Page<Room> searchAvailableRooms(
            @Param("roomTypeName") String roomTypeName,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate,
            @Param("adultCapacity") int adultCapacity,
            @Param("childCapacity") int childCapacity,
            @Param("babyCapacity") int babyCapacity,
            Pageable pageable
    );

}
