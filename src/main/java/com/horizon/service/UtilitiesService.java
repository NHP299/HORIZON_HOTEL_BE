package com.horizon.service;

import com.horizon.dto.UtilitiesDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UtilitiesService {

    UtilitiesDto createUtilities(UtilitiesDto utilitiesDto);
    UtilitiesDto updateUtilities(Integer utilitiesId, UtilitiesDto utilitiesDto);
    void deleteUtilities(Integer utilitiesId);
    UtilitiesDto getUtilitiesById(Integer utilitiesId);
    Page<UtilitiesDto> getAllUtilities(Pageable pageable);
    Page<UtilitiesDto> getUtilitiesByName(String name, Pageable pageable);
    Page<UtilitiesDto> getUtilitiesByRoomTypeName(String roomTypeName, Pageable pageable);
    Page<UtilitiesDto> getUtilitiesByRoomId(Integer roomId, Pageable pageable);
    Page<UtilitiesDto> getUtilitiesByRoomName(String roomName, Pageable pageable);
}
