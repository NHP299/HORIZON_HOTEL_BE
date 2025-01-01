package com.horizon.service;

import com.horizon.dto.RoomTypeDto;
import java.util.List;
import java.util.Map;

public interface RoomTypeService {

    RoomTypeDto create(RoomTypeDto roomTypeDto);

    RoomTypeDto getById(Integer id);

    RoomTypeDto update(Integer roomId, RoomTypeDto roomDto);

    void delete(Integer id);

    List<RoomTypeDto> getAll();
    List<Map<String, Object>> getRoomTypeMedia();
    List<Map<String, Object>> getAllDetail();

}
