package com.horizon.repository;

import com.horizon.domain.RoomTypeUtilities;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoomTypeUtilitiesRepository extends JpaRepository<RoomTypeUtilities, Integer> {
    Optional<RoomTypeUtilities> findByRoomTypeIdAndUtilityId(Integer roomTypeId, Integer utilityId);

    @Transactional
    @Modifying
    @Query("UPDATE RoomTypeUtilities rtu SET rtu.isActivated = false WHERE rtu.roomType.id = :roomTypeId AND rtu.utility.id NOT IN :inputUtilityIds")
    void deactivate(@Param("roomTypeId") Integer roomTypeId, @Param("inputUtilityIds") Set<Integer> inputUtilityIds);

    @Transactional
    @Modifying
    @Query("UPDATE RoomTypeUtilities rtu SET rtu.isActivated = false WHERE rtu.roomType.id = :roomTypeId")
    void deactivateAll(@Param("roomTypeId") Integer roomTypeId);

    @Query(value = "SELECT rt.Id AS roomtypeId, " +
            "rt.name AS roomtypeName, " +
            "STRING_AGG(u.name, ', ') AS utilities " +
            "FROM room_type rt " +
            "JOIN room_type_utilities rtu ON rt.Id = rtu.room_type_Id " +
            "JOIN utilities u ON rtu.utility_Id = u.Id " +
            "WHERE rtu.is_activated = true AND u.is_activated = true" +
            "GROUP BY rt.Id, rt.name;",
            nativeQuery = true)
    List<Map<String, Object>> findRoomTypeUtilities();

    @Query(value = "SELECT rt.Id AS roomtypeId, " +
            "rt.name AS roomtypeName, " +
            "STRING_AGG(u.name, ', ') AS utilities " +
            "FROM room_type rt " +
            "JOIN room_type_utilities rtu ON rt.Id = rtu.room_type_Id " +
            "JOIN utilities u ON rtu.utility_Id = u.Id " +
            "WHERE rtu.is_activated = true AND u.is_activated = true" +
            "AND rt.Id = :roomTypeId " +
            "GROUP BY rt.Id, rt.name;",
            nativeQuery = true)
    Map<String, Object> findByRoomTypeId(@Param("roomTypeId") Integer roomTypeId);

}
