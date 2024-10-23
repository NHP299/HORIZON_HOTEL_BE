package com.horizon.service.impl;

import com.horizon.domain.RoomType;
import com.horizon.domain.Utilities;
import com.horizon.dto.UtilitiesDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.UtilitiesMapper;
import com.horizon.repository.RoomTypeRepository;
import com.horizon.repository.UtilitiesRepository;
import com.horizon.service.UtilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class UtilitiesServiceImpl implements UtilitiesService {

    private final UtilitiesRepository utilitiesRepository;
    private final RoomTypeRepository roomTypeRepository;

    @Autowired
    private UtilitiesServiceImpl(UtilitiesRepository utilitiesRepository, RoomTypeRepository roomTypeRepository) {
        this.utilitiesRepository = utilitiesRepository;
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public UtilitiesDto createUtilities(UtilitiesDto utilitiesDto) {
        Utilities utilities = UtilitiesMapper.mapToUtilities(utilitiesDto);
        Utilities saveUtilities = utilitiesRepository.save(utilities);
        return UtilitiesMapper.mapToUtilitiesDto(saveUtilities);
    }

    @Override
    public UtilitiesDto updateUtilities(Integer utilitiesId, UtilitiesDto updateUtilities) {
        Utilities utilities = utilitiesRepository.findById(utilitiesId).orElseThrow(
                () -> new ResourceNotFoundException("Utilities is not exist with given id: " + utilitiesId));
        RoomType roomType = roomTypeRepository.findById(updateUtilities.getRoomTypeId()).orElseThrow(
                () -> new ResourceNotFoundException("RoomType is not exist with given id: " + updateUtilities.getRoomTypeId()));
        utilities.setRoomType(roomType);
        utilities.setName(updateUtilities.getName());
        utilities.setDescription(updateUtilities.getDescription());

        Utilities updateUtilitiesObj = utilitiesRepository.save(utilities);
        return UtilitiesMapper.mapToUtilitiesDto(updateUtilitiesObj);
    }

    @Override
    public void deleteUtilities(Integer utilitiesId) {
        Utilities utilities = utilitiesRepository.findById(utilitiesId).orElseThrow(
                () -> new ResourceNotFoundException("Utilities is not exist with given id: " + utilitiesId));
        utilitiesRepository.delete(utilities);
    }

    @Override
    public UtilitiesDto getUtilitiesById(Integer utilitiesId) {
        Utilities utilities = utilitiesRepository.findById(utilitiesId).orElseThrow(
                () -> new ResourceNotFoundException("Utilities is not exist with given id: " + utilitiesId));
        return UtilitiesMapper.mapToUtilitiesDto(utilities);
    }

    @Override
    public List<UtilitiesDto> getAllUtilities() {
        List<Utilities> utilities = utilitiesRepository.findAll();
        return utilities.stream().map((utility) -> UtilitiesMapper.mapToUtilitiesDto(utility)).collect(Collectors.toList());
    }

}
