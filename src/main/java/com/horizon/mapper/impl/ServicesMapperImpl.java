package com.horizon.mapper.impl;

import com.horizon.domain.Services;
import com.horizon.dto.ServicesDto;
import com.horizon.mapper.ServicesMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServicesMapperImpl implements ServicesMapper {

    @Override
    public ServicesDto mapToServicesDto(Services services) {
        if (services == null) {
            return null;
        }
        ServicesDto servicesDto = new ServicesDto();
        servicesDto.setId(services.getId());
        servicesDto.setName(services.getName());
        servicesDto.setDescription(services.getDescription());
        servicesDto.setStartedTime(services.getStartedTime());
        servicesDto.setEndTime(services.getEndTime());
        servicesDto.setIsActivated(services.getIsActivated());
        return servicesDto;
    }

    @Override
    public Services mapToService(ServicesDto servicesDto) {
        if (servicesDto == null) {
            return null;
        }
        Services services = new Services();
        services.setId(servicesDto.getId());
        services.setName(servicesDto.getName());
        services.setDescription(servicesDto.getDescription());
        services.setStartedTime(servicesDto.getStartedTime());
        services.setEndTime(servicesDto.getEndTime());
        services.setIsActivated(servicesDto.getIsActivated());
        return services;
    }

}
