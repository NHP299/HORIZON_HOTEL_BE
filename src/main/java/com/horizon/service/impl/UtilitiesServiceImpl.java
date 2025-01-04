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
import java.util.Optional;

@Service
@AllArgsConstructor
public class UtilitiesServiceImpl implements UtilitiesService {

    private UtilitiesRepository utilitiesRepository;
    private UtilitiesMapper utilitiesMapper;

    @Override
    public UtilitiesDto create(UtilitiesDto utilitiesDto) {
        Optional<Utilities> existingUtilities = utilitiesRepository.findByNameIgnoreCaseAndIsActivatedFalse((utilitiesDto.getName()));

        Utilities utilities;
        if (existingUtilities.isPresent()) {
            utilities = utilitiesMapper.mapToUtilities(utilitiesDto);
            utilities.setId(existingUtilities.get().getId());
            utilities.setIsActivated(true);
        }else{
            utilities = utilitiesMapper.mapToUtilities(utilitiesDto);
            utilities.setIsActivated(true);
        }

        Utilities savedUtilities = utilitiesRepository.save(utilities);
        return utilitiesMapper.mapToUtilitiesDto(savedUtilities);
    }

    @Override
    public UtilitiesDto update(Integer utilitiesId, UtilitiesDto utilitiesDto) {
        Utilities updatedUtilities = utilitiesRepository.findById(utilitiesId)
                .map(existingUtilities -> {
                    existingUtilities = utilitiesMapper.mapToUtilities(utilitiesDto);
                    existingUtilities.setId(utilitiesId);
                    existingUtilities.setIsActivated(true);
                    return utilitiesRepository.save(existingUtilities);
                }).orElseThrow(() -> new ResourceNotFoundException("Utilities not found " + utilitiesId));
        return utilitiesMapper.mapToUtilitiesDto(updatedUtilities);
    }

    @Override
    public void delete(Integer utilitiesId) {
        Utilities utilities = utilitiesRepository.findById(utilitiesId).orElseThrow(
                () -> new ResourceNotFoundException("Utilities not found" + utilitiesId));
        utilities.setIsActivated(false);
        utilitiesRepository.save(utilities);
    }

    @Override
    public UtilitiesDto getById(Integer utilitiesId) {
        Utilities utilities = utilitiesRepository.findById(utilitiesId).orElseThrow(
                () -> new ResourceNotFoundException("Utilities is not exist with given id: " + utilitiesId));
        return utilitiesMapper.mapToUtilitiesDto(utilities);
    }

    @Override
    public Page<UtilitiesDto> getAll(Pageable pageable) {
        Page<Utilities> utilitiesPage = utilitiesRepository.findAllByIsActivatedTrue(pageable);
        return utilitiesPage.map(utilitiesMapper::mapToUtilitiesDto);
    }

    @Override
    public Page<UtilitiesDto> getByName(String name, Pageable pageable) {
        Page<Utilities> utilitiesPage = utilitiesRepository.findByNameContainingIgnoreCaseAndIsActivatedTrue(name, pageable);
        return utilitiesPage.map(utilitiesMapper::mapToUtilitiesDto);
    }

}
