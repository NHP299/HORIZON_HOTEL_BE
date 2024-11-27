package com.horizon.mapper.impl;

import com.horizon.domain.RoomType;
import com.horizon.dto.RoomTypeDto;
import com.horizon.mapper.RoomTypeMapper;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeMapperImpl implements RoomTypeMapper {
    @Override
    public RoomTypeDto mapToRoomTypeDto(RoomType roomType) {
        return new RoomTypeDto(roomType.getId(),
                roomType.getName(),
                roomType.getDescription());
    }

    @Override
    public RoomType mapToRoomType(RoomTypeDto roomTypeDto) {
        return new RoomType(roomTypeDto.getId(),
                roomTypeDto.getName(),
                roomTypeDto.getDescription());
    }



}
