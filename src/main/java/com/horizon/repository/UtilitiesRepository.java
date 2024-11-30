package com.horizon.repository;

import com.horizon.domain.Utilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilitiesRepository extends JpaRepository<Utilities, Integer> {
    List<Utilities> findByNameContainingIgnoreCase(String name);
    List<Utilities> findByRoomType_NameContainingIgnoreCase(String roomTypeName);

    @Query("SELECT u FROM Utilities u " +
            "JOIN u.roomType rt " +
            "JOIN Room r ON r.roomType = rt " +
            "WHERE r.id = :roomId")
    List<Utilities> findByRoomId(@Param("roomId") Integer roomId);

    @Query("SELECT u FROM Utilities u " +
            "JOIN u.roomType rt " +
            "JOIN Room r ON r.roomType = rt " +
            "WHERE r.name = :roomName")
    List<Utilities> findByRoomName(@Param("roomName") String roomName);

}
