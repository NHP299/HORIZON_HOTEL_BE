package com.horizon.service.impl;

import com.horizon.domain.Room;
import com.horizon.domain.RoomType;
import com.horizon.dto.RoomDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.RoomMapper;
import com.horizon.repository.RoomRepository;
import com.horizon.repository.RoomTypeRepository;
import com.horizon.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;
    private RoomTypeRepository roomTypeRepository;


    @Override
    public Page<RoomDto> getAllRooms(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Room> rooms = roomRepository.findByIsActivatedTrue(pageable);
        return rooms.map(RoomMapper.INSTANCE::toRoomDto);
    }

    @Override
    public RoomDto createRoom(RoomDto roomDto) {
        Room room = RoomMapper.INSTANCE.toRoom(roomDto);
        room.setIsActivated(true);
        room.setStatus(true);
        Room saveRoom = roomRepository.save(room);
        return RoomMapper.INSTANCE.toRoomDto(saveRoom);
    }

    @Override
    public RoomDto getRoomById(Integer id) {
        return Optional.ofNullable(roomRepository.findByIsActivatedTrueAndId(id))
                .map(RoomMapper.INSTANCE::toRoomDto)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }

    @Override
    public Page<RoomDto> findRoomByName(String name, Pageable pageable) {
        Page<Room> rooms = roomRepository.findByNameContainingAndIsActivatedTrue(name, pageable);
        return rooms.map(RoomMapper.INSTANCE::toRoomDto);
    }

    @Override
    public Page<RoomDto> findRoom(String input, Pageable pageable) {
        try {
            Integer id = Integer.valueOf(input);
            Optional<Room> room = Optional.ofNullable(roomRepository.findByIsActivatedTrueAndId(id));
            if (room.isPresent()) {
                return new PageImpl<>(Collections.singletonList(RoomMapper.INSTANCE.toRoomDto(room.get())), pageable,
                        1);
            }
        } catch (NumberFormatException e) {
        }
        Page<Room> rooms = roomRepository.findByNameContainingAndIsActivatedTrue(input, pageable);
        return rooms.map(RoomMapper.INSTANCE::toRoomDto);
    }

    @Override
    public RoomDto updateRoom(Integer roomId, RoomDto roomDto) {
        Room updatedRoom = Optional.ofNullable(roomRepository.findByIsActivatedTrueAndId(roomId))
                .map(existingRoom -> {
                    existingRoom = RoomMapper.INSTANCE.toRoom(roomDto);
                    return roomRepository.save(existingRoom);
                }).orElseThrow(() -> new ResourceNotFoundException("Room not found" + roomId));
        return RoomMapper.INSTANCE.toRoomDto(updatedRoom);
    }

    @Override
    public void deleteRoom(Integer roomId) {
        Room deletedRoom = Optional.ofNullable(roomRepository.findByIsActivatedTrueAndId(roomId))
                .orElseThrow(() -> new ResourceNotFoundException("Room not found" + roomId));
        deletedRoom.setIsActivated(false);
        roomRepository.save(deletedRoom);
    }

}
