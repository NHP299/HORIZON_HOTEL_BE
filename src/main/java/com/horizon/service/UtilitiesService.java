package com.horizon.service;

import com.horizon.domain.Utilities;
import com.horizon.dto.UtilitiesDto;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface UtilitiesService {

    UtilitiesDto createUtilities(UtilitiesDto utilitiesDto);
    UtilitiesDto updateUtilities(Integer utilitiesId, UtilitiesDto utilitiesDto);
    void deleteUtilities(Integer utilitiesId);
    UtilitiesDto getUtilitiesById(Integer utilitiesId);
    Page<UtilitiesDto> getAllUtilities(Pageable pageable);
    Page<UtilitiesDto> getUtilitiesByName(String name, Pageable pageable);
    Page<UtilitiesDto> getUtilitiesByRoomTypeName(String roomTypeName, Pageable pageable);
    Page<UtilitiesDto> getUtilitiesByRoomName(String RoomName, Pageable pageable);
}
