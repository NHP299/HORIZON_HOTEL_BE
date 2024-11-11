package com.horizon.repository;

import com.horizon.domain.Services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Integer> {
    Page<Services> findByDescriptionContainingIgnoreCase(String description, Pageable pageable);
    Page<Services> findByRoomType_NameContainingIgnoreCase(String roomTypeName, Pageable pageable);

    @Query("SELECT s FROM Services s " +
            "JOIN s.roomType rt " +
            "JOIN rt.roomList r " +
            "WHERE r.id = :roomId " +
            "AND CURRENT_TIMESTAMP BETWEEN s.startedTime AND s.endTime")
    Page<Services> findServicesByRoomId(@Param("roomId") Integer roomId, Pageable pageable);

    @Query("SELECT s FROM Services s " +
            "JOIN s.roomType rt " +
            "JOIN rt.roomList r " +
            "WHERE r.name = :roomName " +
            "AND CURRENT_TIMESTAMP BETWEEN s.startedTime AND s.endTime")
    Page<Services> findServicesByRoomName(@Param("roomName") String roomName, Pageable pageable);

}
