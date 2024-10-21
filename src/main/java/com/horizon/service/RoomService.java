package com.horizon.service;

import com.horizon.dto.RoomDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomService {

    Page<RoomDto> getAllRooms(int page, int size);

    RoomDto createRoom(RoomDto roomDto);

    RoomDto getRoomById(Integer id);

    Page<RoomDto> findRoomByName(String name, Pageable pageable);

    Page<RoomDto> findRoom(String input, Pageable pageable);

    RoomDto updateRoom(Integer roomId, RoomDto roomDto);

    void deleteRoom(Integer roomId);
}
