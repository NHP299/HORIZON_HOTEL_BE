package com.horizon.service.impl;


import com.horizon.domain.Utilities;
import com.horizon.dto.UtilitiesDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.UtilitiesMapper;
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
    public UtilitiesDto create(UtilitiesDto utilitiesDto) {
        Utilities utilities = utilitiesMapper.mapToUtilities(utilitiesDto,null);
        Utilities saveUtilities = utilitiesRepository.save(utilities);
        return utilitiesMapper.mapToUtilitiesDto(saveUtilities);
    }

    @Override
    public UtilitiesDto update(Integer utilitiesId, UtilitiesDto utilitiesDto) {
        Utilities existingUtilities = utilitiesRepository.findById(utilitiesId).orElseThrow(() -> new ResourceNotFoundException("Utilities not found " + utilitiesId));
        Utilities updatedUtilities = utilitiesMapper.mapToUtilities(utilitiesDto,existingUtilities);
        updatedUtilities = utilitiesRepository.save(updatedUtilities);
        return utilitiesMapper.mapToUtilitiesDto(updatedUtilities);
    }


    @Override
    public void delete(Integer utilitiesId) {
        Utilities utilities = utilitiesRepository.findById(utilitiesId).orElseThrow(
                () -> new ResourceNotFoundException("Utilities not found" + utilitiesId));
        utilitiesRepository.delete(utilities);
    }

    @Override
    public UtilitiesDto getById(Integer utilitiesId) {
        Utilities utilities = utilitiesRepository.findById(utilitiesId).orElseThrow(
                () -> new ResourceNotFoundException("Utilities is not exist with given id: " + utilitiesId));
        return utilitiesMapper.mapToUtilitiesDto(utilities);
    }

    @Override
    public Page<UtilitiesDto> getAll(Pageable pageable) {
        Page<Utilities> utilitiesPage = utilitiesRepository.findAll(pageable);
        return utilitiesPage.map(utilitiesMapper::mapToUtilitiesDto);
    }

    @Override
    public Page<UtilitiesDto> getByName(String name, Pageable pageable) {
        Page<Utilities> utilitiesPage = utilitiesRepository.findByNameContainingIgnoreCase(name, pageable);
        return utilitiesPage.map(utilitiesMapper::mapToUtilitiesDto);
    }

    @Override
    public Page<UtilitiesDto> getByRoomTypeName(String roomTypeName, Pageable pageable) {
        Page<Utilities> utilitiesPage = utilitiesRepository.findByRoomType_NameContainingIgnoreCase(roomTypeName, pageable);
        return utilitiesPage.map(utilitiesMapper::mapToUtilitiesDto);
    }

    @Override
    public Page<UtilitiesDto> getByRoomId(Integer roomId, Pageable pageable) {
        Page<Utilities> utilitiesPage = utilitiesRepository.findByRoomId(roomId, pageable);
        return utilitiesPage.map(utilitiesMapper::mapToUtilitiesDto);
    }

    @Override
    public Page<UtilitiesDto> getByRoomName(String roomName, Pageable pageable) {
        Page<Utilities> utilitiesPage = utilitiesRepository.findByRoomName(roomName, pageable);
        return utilitiesPage.map(utilitiesMapper::mapToUtilitiesDto);
    }

}
