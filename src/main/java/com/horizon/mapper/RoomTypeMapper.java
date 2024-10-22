package com.horizon.mapper;

import com.horizon.domain.RoomType;
import com.horizon.dto.RoomTypeDto;

public class RoomTypeMapper {

    public static RoomTypeDto mapToRoomTypeDto(RoomType roomType) {
        return new RoomTypeDto(roomType.getId(),
                roomType.getName(),
                roomType.getDescription());
    }

    public static RoomType mapToRoomType(RoomTypeDto roomTypeDto) {
        return new RoomType(roomTypeDto.getId(),
                roomTypeDto.getName(),
                roomTypeDto.getDescription());
    }
}
