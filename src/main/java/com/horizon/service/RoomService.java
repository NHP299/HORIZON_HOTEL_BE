package com.horizon.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.horizon.dto.RoomDto;

public interface RoomService {

    List<RoomDto> getAll();

    List<RoomDto> getAllIsActivated();

    RoomDto create(RoomDto roomDto);

    RoomDto getById(Integer id);

    List<RoomDto> findByName(String name);

    List<RoomDto> find(String input);

    RoomDto update(Integer roomId, RoomDto roomDto);

    void delete(Integer roomId);

    List<RoomDto> getByRoomTypeName(String roomTypeName);

    List<RoomDto> getByStatus(String statusDescription);

    List<RoomDto> getIsAvailable();

    List<RoomDto> findAvailable(LocalDate startDate, LocalDate endDate);

    Page<RoomDto> findAvailableRooms(LocalDate startDate, LocalDate endDate, Pageable pageable);

    List<Map<String, Object>> getRoomDetail();
}
