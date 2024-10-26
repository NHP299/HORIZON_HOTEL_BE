package com.horizon.mapper.impl;

import com.horizon.domain.RoomType;
import com.horizon.domain.Services;
import com.horizon.dto.ServicesDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.ServicesMapper;
import com.horizon.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicesMapperImpl implements ServicesMapper {

    private final RoomTypeRepository roomTypeRepository;

    @Autowired
    public ServicesMapperImpl(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public ServicesDto mapToServicesDto(Services services) {
        if (services == null) {
            return null;
        }
        ServicesDto servicesDto = new ServicesDto();
        servicesDto.setId(services.getId());
        servicesDto.setRoomTypeId(services.getRoomType().getId());
        servicesDto.setDescription(services.getDescription());
        servicesDto.setStartedTime(services.getStartedTime());
        servicesDto.setEndTime(services.getEndTime());
        return servicesDto;
    }

    @Override
    public Services mapToService(ServicesDto servicesDto) {
        if (servicesDto == null) {
            return null;
        }
        Services services = new Services();
        services.setId(servicesDto.getId());
        services.setRoomType(findRoomTypeById(servicesDto.getRoomTypeId()));
        services.setDescription(servicesDto.getDescription());
        services.setStartedTime(servicesDto.getStartedTime());
        services.setEndTime(servicesDto.getEndTime());

        return services;
    }

    private RoomType findRoomTypeById(Integer id) {
        return roomTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("RoomType not found with id: " + id));
    }

}
