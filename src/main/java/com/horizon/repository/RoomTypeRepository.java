package com.horizon.repository;

import com.horizon.domain.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {

    Optional<RoomType> findByName(String name);

    @Query(value = "SELECT rt.id, rt.name, STRING_AGG(m.path, ', ') AS paths " +
            "FROM room_type rt " +
            "LEFT JOIN media m ON rt.id = m.room_type_id " +
            "GROUP BY rt.id, rt.name",
            nativeQuery = true)
    List<Map<String, Object>> findRoomTypeMedia();
}
