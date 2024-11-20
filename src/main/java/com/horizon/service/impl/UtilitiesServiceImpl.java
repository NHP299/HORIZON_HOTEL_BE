package com.horizon.service.impl;


import com.horizon.domain.Utilities;
import com.horizon.dto.UtilitiesDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.UtilitiesMapper;
import com.horizon.repository.UtilitiesRepository;
import com.horizon.service.UtilitiesService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;


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
    public List<UtilitiesDto> getAll() {
        List<Utilities> utilitiesPage = utilitiesRepository.findAll();
        return utilitiesPage.stream().map(utilitiesMapper::mapToUtilitiesDto).toList();
    }

    @Override
    public List<UtilitiesDto> getByName(String name) {
        List<Utilities> utilitiesPage = utilitiesRepository.findByNameContainingIgnoreCase(name);
        return utilitiesPage.stream().map(utilitiesMapper::mapToUtilitiesDto).toList();
    }

    @Override
    public List<UtilitiesDto> getByRoomTypeName(String roomTypeName) {
        List<Utilities> utilitiesPage = utilitiesRepository.findByRoomType_NameContainingIgnoreCase(roomTypeName);
        return utilitiesPage.stream().map(utilitiesMapper::mapToUtilitiesDto).toList();
    }

    @Override
    public List<UtilitiesDto> getByRoomId(Integer roomId) {
        List<Utilities> utilitiesPage = utilitiesRepository.findByRoomId(roomId);
        return utilitiesPage.stream().map(utilitiesMapper::mapToUtilitiesDto).toList();
    }

    @Override
    public List<UtilitiesDto> getByRoomName(String roomName) {
        List<Utilities> utilitiesPage = utilitiesRepository.findByRoomName(roomName);
        return utilitiesPage.stream().map(utilitiesMapper::mapToUtilitiesDto).toList();
    }

}
