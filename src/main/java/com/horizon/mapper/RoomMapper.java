package com.horizon.mapper;

import com.horizon.domain.Room;
import com.horizon.dto.RoomDto;

public interface RoomMapper {

    RoomDto toRoomDto(Room room);

    Room toRoom(RoomDto roomDto);

}
