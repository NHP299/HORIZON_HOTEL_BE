package com.horizon.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.horizon.dto.RoomDto;
import org.springframework.scheduling.annotation.Scheduled;

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

    List<Map<String, Object>> getRoomDetail();

    List<RoomDto> search(String roomTypeName, LocalDate checkIn, LocalDate checkOut, int guestCount, int roomCount);

    @Scheduled(fixedRate = 60000)
    void updateRoomStatuses();
}
