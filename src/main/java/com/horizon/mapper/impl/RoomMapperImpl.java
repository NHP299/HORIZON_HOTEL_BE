package com.horizon.mapper.impl;

import com.horizon.domain.Room;
import com.horizon.domain.RoomType;
import com.horizon.dto.RoomDto;
import com.horizon.mapper.RoomMapper;
import com.horizon.repository.RoomTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomMapperImpl implements RoomMapper {

    private RoomTypeRepository roomTypeRepository;

    @Override
    public RoomDto toRoomDto(Room room) {
        if (room == null) {
            return null;
        }

        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setRoomTypeId(room.getRoomType() != null ? room.getRoomType().getId() : null);
        roomDto.setName(room.getName());
        roomDto.setStatus(room.getStatus());
        roomDto.setFloor(room.getFloor());
        roomDto.setPrice(room.getPrice());
        roomDto.setDescription(room.getDescription());
        roomDto.setIsActivated(room.getIsActivated());

        return roomDto;
    }

    @Override
    public Room toRoom(RoomDto roomDto) {
        if (roomDto == null) {
            return null;
        }

        Room room = new Room();
        room.setId(roomDto.getId());

        RoomType roomType = findRoomTypeById(roomDto.getRoomTypeId()); // Cần triển khai phương thức này
        room.setRoomType(roomType);

        room.setName(roomDto.getName());
        room.setStatus(roomDto.getStatus());
        room.setFloor(roomDto.getFloor());
        room.setPrice(roomDto.getPrice());
        room.setDescription(roomDto.getDescription());
        room.setIsActivated(roomDto.getIsActivated());

        return room;
    }

    private RoomType findRoomTypeById(Integer id) {
        return roomTypeRepository.findById(id).orElse(null);
    }

}
