package com.horizon.service;

import com.horizon.dto.RoomTypeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface RoomTypeService {

    RoomTypeDto create(RoomTypeDto roomTypeDto);

    RoomTypeDto getById(Integer id);

    RoomTypeDto update(Integer roomId, RoomTypeDto roomDto);

    void delete(Integer id);

    Page<RoomTypeDto> getAll(Pageable pageable);
    List<Map<String, Object>> getRoomTypeMedia();
    List<Map<String, Object>> getAllDetail();

}
