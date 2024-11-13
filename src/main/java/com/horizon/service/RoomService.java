package com.horizon.service;

import com.horizon.dto.RoomDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    List<RoomDto> getAll();

    List<RoomDto> getAllIsActivated();

    RoomDto create(RoomDto roomDto);

    RoomDto getById(Integer id);

    List<RoomDto> findByName(String name);

    List<RoomDto> find(String input);

    RoomDto update(Integer roomId, RoomDto roomDto);

    void delete(Integer roomId);

    List<RoomDto> getByRoomTypeName(String roomTypeName);

    List<RoomDto> getByStatus(String statusDescription);

    List<RoomDto> getIsAvailable();

    List<RoomDto> findAvailable(LocalDate startDate, LocalDate endDate);
}
