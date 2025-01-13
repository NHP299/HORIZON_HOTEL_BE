package com.horizon.service;

import com.horizon.dto.RoomTypeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface RoomTypeService {

    RoomTypeDto create(RoomTypeDto roomTypeDto);

    RoomTypeDto getById(Integer id);

    RoomTypeDto update(Integer id, RoomTypeDto roomDto);

    void delete(Integer id);

    Page<RoomTypeDto> getAll(Pageable pageable);
    Page<Map<String, Object>> getRoomTypeMedia(Pageable pageable);
    Page<Map<String, Object>> getAllDetail(Pageable pageable);
    Page<Map<String, Object>> getDetailById(Integer id, Pageable pageable);
}
