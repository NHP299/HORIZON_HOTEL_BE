package com.horizon.service;


import com.horizon.domain.RoomType;
import com.horizon.dto.RoomTypeDto;

import java.util.List;

public interface RoomTypeService {

    RoomTypeDto crateRoomType(RoomTypeDto roomTypeDto);

    RoomTypeDto getRoomTypeById(Integer id);

    List<RoomTypeDto> getAllRoomType();

    RoomTypeDto updateRoom(Integer roomId, RoomTypeDto roomDto);

    void deleteRoomType(Integer id);

    RoomTypeDto getRoomTypeByName(String name);
}
