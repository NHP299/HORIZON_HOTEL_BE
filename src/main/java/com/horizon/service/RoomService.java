package com.horizon.service;

import com.horizon.dto.RoomDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    List<RoomDto> getAll();

    Page<RoomDto> getAll(Pageable pageable);

    RoomDto create(RoomDto roomDto);

    RoomDto getById(Integer id);

    Page<RoomDto> findByName(String name, Pageable pageable);

    Page<RoomDto> find(String input, Pageable pageable);

    RoomDto update(Integer roomId, RoomDto roomDto);

    void delete(Integer roomId);

    Page<RoomDto> getByRoomTypeName(String roomTypeName, Pageable pageable);

    Page<RoomDto> getByStatus(String statusDescription, Pageable pageable);

    Page<RoomDto> getIsAvailable(Pageable pageable);

    Page<RoomDto> findAvailable(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
