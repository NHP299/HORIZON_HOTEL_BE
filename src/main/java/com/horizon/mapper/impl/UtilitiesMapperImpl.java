package com.horizon.mapper.impl;

import com.horizon.domain.RoomType;
import com.horizon.domain.Utilities;
import com.horizon.dto.UtilitiesDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.UtilitiesMapper;
import com.horizon.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilitiesMapperImpl implements UtilitiesMapper {

    private final RoomTypeRepository roomTypeRepository;

    @Autowired
    public UtilitiesMapperImpl(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public UtilitiesDto mapToUtilitiesDto(Utilities utilities) {
        if (utilities == null) {
            return null;
        }
        UtilitiesDto utilitiesDto = new UtilitiesDto();
        utilitiesDto.setId(utilities.getId());
        utilitiesDto.setRoomTypeId(utilities.getRoomType().getId());
        utilitiesDto.setName(utilities.getName());
        utilitiesDto.setDescription(utilities.getDescription());

        return utilitiesDto;
    }

    @Override
    public Utilities mapToUtilities(UtilitiesDto utilitiesDto, Utilities existingUtilities) {
        if (utilitiesDto == null) {
            return null;
        }
        Utilities utilities = (existingUtilities != null) ? existingUtilities : new Utilities();
        if (existingUtilities == null) {
            utilities.setId(utilitiesDto.getId());
        }

        RoomType roomType = findRoomTypeById(utilitiesDto.getRoomTypeId());
        utilities.setRoomType(roomType);
        utilities.setName(utilitiesDto.getName());
        utilities.setDescription(utilitiesDto.getDescription());

        return utilities;
    }

    private RoomType findRoomTypeById(Integer id) {
        return roomTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("RoomType not found with id: " + id));
    }
}
