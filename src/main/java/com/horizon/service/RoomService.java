package com.horizon.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.horizon.domain.Room;
import com.horizon.dto.RoomDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;

public interface RoomService {

    Page<RoomDto> getAll(Pageable pageable);

    Page<RoomDto> getAllIsActivated(Pageable pageable);

    RoomDto create(RoomDto roomDto);

    RoomDto getById(Integer id);

    List<RoomDto> findByName(String name);

    List<RoomDto> find(String input);

    RoomDto update(Integer roomId, RoomDto roomDto);

    void delete(Integer roomId);

    Page<RoomDto> getByRoomTypeName(String roomTypeName,Pageable pageable);

    Page<RoomDto> getByStatus(Room.Status statusDescription,Pageable pageable);

    Page<RoomDto> getIsAvailable(Pageable pageable);

    Page<RoomDto> findAvailable(LocalDate startDate, LocalDate endDate,Pageable pageable);

    List<Map<String, Object>> getRoomDetail();

    Map<String, Object> getRoomDetailById(Integer id);

    Page<RoomDto> search(String roomTypeName,
                      LocalDate checkIn, LocalDate checkOut,
                      int adult, int child, int baby, int roomCount,
                      Pageable pageable);

    @Scheduled(fixedRate = 60000)
    void updateRoomStatuses();
}
