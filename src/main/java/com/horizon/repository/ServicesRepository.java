package com.horizon.repository;

import com.horizon.domain.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Integer> {
    List<Services> findByDescriptionContainingIgnoreCase(String description);
    List<Services> findByRoomType_NameContainingIgnoreCase(String roomTypeName);

    @Query("SELECT s FROM Services s " +
            "JOIN s.roomType rt " +
            "JOIN Room r ON r.roomType = rt " +
            "WHERE r.id = :roomId " +
            "AND CURRENT_TIMESTAMP BETWEEN s.startedTime AND s.endTime")
    List<Services> findByRoomId(@Param("roomId") Integer roomId);



    @Query("SELECT s FROM Services s " +
            "JOIN s.roomType rt " +
            "JOIN Room r ON r.roomType = rt " +
            "WHERE r.name = :roomName " +
            "AND CURRENT_TIMESTAMP BETWEEN s.startedTime AND s.endTime")
    List<Services> findByRoomName(@Param("roomName") String roomName);

}
