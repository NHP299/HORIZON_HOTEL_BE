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
        Utilities utilities = utilitiesMapper.mapToUtilities(utilitiesDto);
        Utilities saveUtilities = utilitiesRepository.save(utilities);
        return utilitiesMapper.mapToUtilitiesDto(saveUtilities);
    }

    @Override
    public UtilitiesDto update(Integer utilitiesId, UtilitiesDto utilitiesDto) {
        Utilities updatedUtilities = utilitiesRepository.findById(utilitiesId)
                .map(existingUtilities -> {
                    existingUtilities = utilitiesMapper.mapToUtilities(utilitiesDto);
                    existingUtilities.setId(utilitiesId);
                    return utilitiesRepository.save(existingUtilities);
                }).orElseThrow(() -> new ResourceNotFoundException("Utilities not found " + utilitiesId));
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

}
