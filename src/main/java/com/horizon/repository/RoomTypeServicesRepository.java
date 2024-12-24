package com.horizon.repository;

import com.horizon.domain.RoomTypeServices;
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
public interface RoomTypeServicesRepository extends JpaRepository<RoomTypeServices, Integer> {
    Optional<RoomTypeServices> findByRoomTypeIdAndServiceId(Integer roomTypeId, Integer serviceId);

    @Transactional
    @Modifying
    @Query("UPDATE RoomTypeServices rts SET rts.isActivated = false WHERE rts.roomType.id = :roomTypeId AND rts.service.id NOT IN :inputServiceIds")
    void deactivate(@Param("roomTypeId") Integer roomTypeId, @Param("inputServiceIds") Set<Integer> inputServiceIds);

    @Transactional
    @Modifying
    @Query("UPDATE RoomTypeServices rts SET rts.isActivated = false WHERE rts.roomType.id = :roomTypeId")
    void deactivateAll(@Param("roomTypeId") Integer roomTypeId);

    @Query(value = "SELECT rt.Id AS roomtypeId, " +
            "rt.name AS roomtypeName, " +
            "STRING_AGG(s.name, ', ') AS services " +
            "FROM room_type rt " +
            "JOIN room_type_services rts ON rt.Id = rts.room_type_Id " +
            "JOIN services s ON rts.service_Id = s.Id " +
            "WHERE rts.is_activated = true " +
            "GROUP BY rt.Id, rt.name;",
            nativeQuery = true)
    List<Map<String, Object>> findRoomTypeService();

    @Query(value = "SELECT rt.Id AS roomtypeId, " +
            "rt.name AS roomtypeName, " +
            "STRING_AGG(s.name, ', ') AS services " +
            "FROM room_type rt " +
            "JOIN room_type_services rts ON rt.Id = rts.room_type_Id " +
            "JOIN services s ON rts.service_Id = s.Id " +
            "WHERE rts.is_activated = true " +
            "AND rt.Id = :roomTypeId " +
            "GROUP BY rt.Id, rt.name;",
            nativeQuery = true)
    Map<String, Object> findByRoomTypeId(@Param("roomTypeId") Integer roomTypeId);


}
