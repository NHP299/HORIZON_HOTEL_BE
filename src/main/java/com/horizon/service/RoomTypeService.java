package com.horizon.service;


import com.horizon.dto.RoomTypeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface RoomTypeService {

    RoomTypeDto crateRoomType(RoomTypeDto roomTypeDto);

    RoomTypeDto getRoomTypeById(Integer id);

    Page<RoomTypeDto> getAllRoomType(Pageable pageable);

    RoomTypeDto updateRoom(Integer roomId, RoomTypeDto roomDto);

    void deleteRoomType(Integer id);

    List<RoomTypeDto> getAllRoomType();
    List<Map<String, Object>> getRoomTypeMedia();

}
