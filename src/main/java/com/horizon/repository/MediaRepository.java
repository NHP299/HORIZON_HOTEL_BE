package com.horizon.repository;

import com.horizon.domain.Media;
import com.horizon.domain.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    Page<Media> findByRoomType(RoomType roomType, Pageable pageable);

    @Query("SELECT m FROM Media m WHERE m.roomType.isActivated = true")
    Page<Media> findAllByRoomTypeIsActivated(Pageable pageable);

}
