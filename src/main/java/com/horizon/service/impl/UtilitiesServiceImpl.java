package com.horizon.service.impl;


import com.horizon.domain.Utilities;
import com.horizon.dto.UtilitiesDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.UtilitiesMapper;
import com.horizon.repository.RoomTypeRepository;
import com.horizon.repository.UtilitiesRepository;
import com.horizon.service.UtilitiesService;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UtilitiesServiceImpl implements UtilitiesService {

    private UtilitiesRepository utilitiesRepository;
    private UtilitiesMapper utilitiesMapper;


    @Override
    public UtilitiesDto createUtilities(UtilitiesDto utilitiesDto) {
        Utilities utilities = utilitiesMapper.mapToUtilities(utilitiesDto);
        Utilities saveUtilities = utilitiesRepository.save(utilities);
        return utilitiesMapper.mapToUtilitiesDto(saveUtilities);
    }

    @Override
    public UtilitiesDto updateUtilities(Integer utilitiesId, UtilitiesDto utilitiesDto) {
        Utilities updatedUtilities = utilitiesRepository.findById(utilitiesId).
                map(existingUtilities -> {
                    existingUtilities = utilitiesMapper.mapToUtilities(utilitiesDto);
                    return utilitiesRepository.save(existingUtilities);
                }).orElseThrow(() -> new ResourceNotFoundException("Utilities not found" + utilitiesId));
        return utilitiesMapper.mapToUtilitiesDto(updatedUtilities);
    }

    @Override
    public void deleteUtilities(Integer utilitiesId) {
        Utilities utilities = utilitiesRepository.findById(utilitiesId).orElseThrow(
                () -> new ResourceNotFoundException("Utilities not found" + utilitiesId));
        utilitiesRepository.delete(utilities);
    }

    @Override
    public UtilitiesDto getUtilitiesById(Integer utilitiesId) {
        Utilities utilities = utilitiesRepository.findById(utilitiesId).orElseThrow(
                () -> new ResourceNotFoundException("Utilities is not exist with given id: " + utilitiesId));
        return utilitiesMapper.mapToUtilitiesDto(utilities);
    }

    @Override
    public Page<UtilitiesDto> getAllUtilities(Pageable pageable) {
        Page<Utilities> utilitiesPage = utilitiesRepository.findAll(pageable);
        return utilitiesPage.map(utilitiesMapper::mapToUtilitiesDto);
    }

    @Override
    public Page<UtilitiesDto> getUtilitiesByName(String name, Pageable pageable) {
        Page<Utilities> utilitiesPage = utilitiesRepository.findByNameContainingIgnoreCase(name, pageable);
        return utilitiesPage.map(utilitiesMapper::mapToUtilitiesDto);
    }

    @Override
    public Page<UtilitiesDto> getUtilitiesByRoomTypeName(String roomTypeName, Pageable pageable) {
        Page<Utilities> utilitiesPage = utilitiesRepository.findByRoomType_NameContainingIgnoreCase(roomTypeName, pageable);
        return utilitiesPage.map(utilitiesMapper::mapToUtilitiesDto);
    }

    @Override
    public Page<UtilitiesDto> getUtilitiesByRoomName(String roomName, Pageable pageable) {
        Page<Utilities> utilitiesPage = utilitiesRepository.findUtilitiesByRoomName(roomName, pageable);
        return utilitiesPage.map(utilitiesMapper::mapToUtilitiesDto);
    }

}
