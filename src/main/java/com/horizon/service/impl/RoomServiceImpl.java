package com.horizon.service.impl;

import com.horizon.domain.Room;
import com.horizon.domain.RoomType;
import com.horizon.dto.RoomDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.RoomMapper;
import com.horizon.repository.RoomRepository;
import com.horizon.repository.RoomTypeRepository;
import com.horizon.service.RoomService;
import com.horizon.validation.RoomInputValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomInputValidator roomInputValidator;
    private RoomRepository roomRepository;
    private RoomTypeRepository roomTypeRepository;
    private RoomMapper roomMapper;

    @Override
    public Page<RoomDto> getAllRooms(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
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
        roomInputValidator.validate(input);
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

}
