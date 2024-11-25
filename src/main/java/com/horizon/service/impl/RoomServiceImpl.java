package com.horizon.service.impl;

import com.horizon.domain.Room;
import com.horizon.domain.RoomStatus;
import com.horizon.dto.RoomDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.RoomMapper;
import com.horizon.repository.RoomRepository;
import com.horizon.repository.RoomTypeRepository;
import com.horizon.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;
    private RoomTypeRepository roomTypeRepository;
    private RoomMapper roomMapper;

    @Override
    public List<RoomDto> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .map(roomMapper::toRoomDto)
                .toList();
    }

    @Override
    public Page<RoomDto> getAllRooms(Pageable pageable) {
        Page<Room> rooms = roomRepository.findByIsActivatedTrue(pageable);
        return rooms.map(roomMapper::toRoomDto);
    }

    @Override
    public RoomDto createRoom(RoomDto roomDto) {
        roomDto.setStatus("Available");
        Room room = roomMapper.toRoom(roomDto);
        room.setIsActivated(true);
        Room saveRoom = roomRepository.save(room);
        return roomMapper.toRoomDto(saveRoom);
    }

    @Override
    public RoomDto getRoomById(Integer id) {
        return Optional.ofNullable(roomRepository.findByIsActivatedTrueAndId(id))
                .map(roomMapper::toRoomDto)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }

    @Override
    public Page<RoomDto> findRoomByName(String name, Pageable pageable) {
        Page<Room> rooms = roomRepository.findByNameContainingIgnoreCaseAndIsActivatedTrue(name, pageable);
        return rooms.map(roomMapper::toRoomDto);
    }

    @Override
    public Page<RoomDto> findRoom(String input, Pageable pageable) {
        try {
            Integer id = Integer.valueOf(input);
            Optional<Room> room = Optional.ofNullable(roomRepository.findByIsActivatedTrueAndId(id));
            if (room.isPresent()) {
                return new PageImpl<>(Collections.singletonList(roomMapper.toRoomDto(room.get())), pageable,
                        1);
            }
        } catch (NumberFormatException e) {
        }
        Page<Room> rooms = roomRepository.findByNameContainingIgnoreCaseAndIsActivatedTrue(input, pageable);
        return rooms.map(roomMapper::toRoomDto);
    }

    @Override
    public RoomDto updateRoom(Integer roomId, RoomDto roomDto) {
        Room updatedRoom = Optional.ofNullable(roomRepository.findByIsActivatedTrueAndId(roomId))
                .map(existingRoom -> {
                    existingRoom = roomMapper.toRoom(roomDto);
                    return roomRepository.save(existingRoom);
                }).orElseThrow(() -> new ResourceNotFoundException("Room not found" + roomId));
        return roomMapper.toRoomDto(updatedRoom);
    }

    @Override
    public void deleteRoom(Integer roomId) {
        Room deletedRoom = Optional.ofNullable(roomRepository.findByIsActivatedTrueAndId(roomId))
                .orElseThrow(() -> new ResourceNotFoundException("Room not found" + roomId));
        deletedRoom.setStatus(3);
        deletedRoom.setIsActivated(false);
        roomRepository.save(deletedRoom);
    }


    @Override
    public Page<RoomDto> getRoomsByRoomTypeName(String roomTypeName, Pageable pageable) {
        return roomRepository.findByRoomTypeName(roomTypeName, pageable)
                .map(roomMapper::toRoomDto);
    }


    @Override
    public Page<RoomDto> getRoomsByStatus(String statusDescription, Pageable pageable) {
        Integer statusCode = RoomStatus.fromDescription(statusDescription);
        return roomRepository.findByStatusAndIsActivatedTrue(statusCode, pageable)
                .map(roomMapper::toRoomDto);
    }


    @Override
    public Page<RoomDto> getRoomsIsAvailable(Pageable pageable) {
        return roomRepository.findByStatusAndIsActivatedTrue(0, pageable)
                .map(roomMapper::toRoomDto);
    }

    @Override
    public Page<RoomDto> findAvailableRooms(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return roomRepository.findAvailableRoomsInDateRange(startDate, endDate, pageable)
                .map(roomMapper::toRoomDto);
    }

    @Override
    public List<Map<String, Object>> getRoomDetail() {
        return roomRepository.getRoomDetail();
    }

}
