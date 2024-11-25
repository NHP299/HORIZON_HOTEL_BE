package com.horizon.service;

import com.horizon.dto.RoomDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface RoomService {

    List<RoomDto> getAllRooms();

    Page<RoomDto> getAllRooms(Pageable pageable);

    RoomDto createRoom(RoomDto roomDto);

    RoomDto getRoomById(Integer id);

    Page<RoomDto> findRoomByName(String name, Pageable pageable);

    Page<RoomDto> findRoom(String input, Pageable pageable);

    RoomDto updateRoom(Integer roomId, RoomDto roomDto);

    void deleteRoom(Integer roomId);

    Page<RoomDto> getRoomsByRoomTypeName(String roomTypeName, Pageable pageable);

    Page<RoomDto> getRoomsByStatus(String statusDescription, Pageable pageable);

    Page<RoomDto> getRoomsIsAvailable(Pageable pageable);

    Page<RoomDto> findAvailableRooms(LocalDate startDate, LocalDate endDate, Pageable pageable);

    List<Map<String, Object>> getRoomDetail();
}
