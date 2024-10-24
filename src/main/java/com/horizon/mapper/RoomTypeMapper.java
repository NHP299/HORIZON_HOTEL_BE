package com.horizon.mapper;

import com.horizon.domain.RoomType;
import com.horizon.dto.RoomTypeDto;

public interface RoomTypeMapper {
    RoomTypeDto mapToRoomTypeDto(RoomType roomType);
    RoomType mapToRoomType(RoomTypeDto roomTypeDto);
}
