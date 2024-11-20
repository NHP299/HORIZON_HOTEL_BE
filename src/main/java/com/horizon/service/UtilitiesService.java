package com.horizon.service;

import com.horizon.dto.UtilitiesDto;

import java.util.List;


public interface UtilitiesService {

    UtilitiesDto create(UtilitiesDto utilitiesDto);
    UtilitiesDto update(Integer utilitiesId, UtilitiesDto utilitiesDto);
    void delete(Integer utilitiesId);
    UtilitiesDto getById(Integer utilitiesId);
    List<UtilitiesDto> getAll();
    List<UtilitiesDto> getByName(String name);
    List<UtilitiesDto> getByRoomTypeName(String roomTypeName);
    List<UtilitiesDto> getByRoomId(Integer roomId);
    List<UtilitiesDto> getByRoomName(String roomName);
}
