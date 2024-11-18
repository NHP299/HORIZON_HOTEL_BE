package com.horizon.repository;

import com.horizon.domain.Utilities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilitiesRepository extends JpaRepository<Utilities, Integer> {
    Page<Utilities> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Utilities> findByRoomType_NameContainingIgnoreCase(String roomTypeName, Pageable pageable);

    @Query("SELECT u FROM Utilities u " +
            "JOIN u.roomType rt " +
            "JOIN rt.roomList r " +
            "WHERE r.id = :roomId")
    Page<Utilities> findByRoomId(@Param("roomId") Integer roomId, Pageable pageable);

    @Query("SELECT u FROM Utilities u " +
            "JOIN u.roomType rt " +
            "JOIN rt.roomList r " +
            "WHERE r.name = :roomName")
    Page<Utilities> findByRoomName(@Param("roomName") String roomName, Pageable pageable);

}
