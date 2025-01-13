package com.horizon.service.impl;

import com.horizon.domain.Services;
import com.horizon.dto.ServicesDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.ServicesMapper;
import com.horizon.repository.ServicesRepository;
import com.horizon.service.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicesServiceImpl implements ServicesService {

    private ServicesRepository servicesRepository;
    private ServicesMapper servicesMapper;


    @Override
    public ServicesDto create(ServicesDto servicesDto) {
        Optional<Services> existingService = servicesRepository.findByNameIgnoreCaseAndIsActivatedFalse(servicesDto.getName());

        Services services;

        if (existingService.isPresent()) {
            services = servicesMapper.mapToService(servicesDto);
            services.setId(existingService.get().getId());
            services.setIsActivated(true);
        } else {
            services = servicesMapper.mapToService(servicesDto);
            services.setIsActivated(true);
        }

        Services savedServices = servicesRepository.save(services);
        return servicesMapper.mapToServicesDto(savedServices);
    }

    @Override
    public ServicesDto update(Integer serviceId, ServicesDto servicesDto) {
        Services updatedServices = servicesRepository.findById(serviceId)
                .map(existingServices -> {
                    existingServices = servicesMapper.mapToService(servicesDto);
                    existingServices.setId(serviceId);
                    existingServices.setIsActivated(true);
                    return servicesRepository.save(existingServices);
                }).orElseThrow(() -> new ResourceNotFoundException("Service not found " + serviceId));
        return servicesMapper.mapToServicesDto(updatedServices);
    }


    @Override
    public void delete(Integer serviceId) {
        Services services = servicesRepository.findById(serviceId).orElseThrow(
                () -> new ResourceNotFoundException("Service not found " + serviceId)
        );
        services.setIsActivated(false);
        servicesRepository.save(services);
    }

    @Override
    public ServicesDto getById(Integer serviceId) {
        Services services = servicesRepository.findById(serviceId).orElseThrow(
                () -> new ResourceNotFoundException("Services is not exist with given id: " + serviceId)
        );
        return servicesMapper.mapToServicesDto(services);
    }

    @Override
    public Page<ServicesDto> getAll(Pageable pageable) {
        Page<Services> servicesPage = servicesRepository.findAllByIsActivatedTrue(pageable);
        return servicesPage.map(servicesMapper::mapToServicesDto);
    }

    @Override
    public Page<ServicesDto> getByName(String name, Pageable pageable) {
        Page<Services> servicesPage = servicesRepository.findByNameAndIsActivatedTrue(name, pageable);
        return servicesPage.map(servicesMapper::mapToServicesDto);
    }

}
