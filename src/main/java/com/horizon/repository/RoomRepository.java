package com.horizon.repository;

import com.horizon.domain.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    Room findByIsActivatedTrueAndId(Integer id);

    Page<Room> findByIsActivatedTrue(Pageable pageable);

    Page<Room> findByNameContainingIgnoreCaseAndIsActivatedTrue(String keywords, Pageable pageable);
}
