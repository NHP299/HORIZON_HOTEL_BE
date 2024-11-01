package com.horizon.repository;

import com.horizon.domain.Media;
import com.horizon.domain.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MediaRepository extends JpaRepository<Media, Integer> {

    List<Media> findByRoomType(RoomType roomType);

    Optional<Media> findById(Integer mediaId);
}
