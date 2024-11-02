package com.horizon.service;


import com.horizon.domain.RoomType;
import com.horizon.dto.RoomTypeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoomTypeService {

    RoomTypeDto crateRoomType(RoomTypeDto roomTypeDto);

    RoomTypeDto getRoomTypeById(Integer id);

    Page<RoomTypeDto> getAllRoomType(Pageable pageable);

    RoomTypeDto updateRoom(Integer roomId, RoomTypeDto roomDto);

    void deleteRoomType(Integer id);

}
