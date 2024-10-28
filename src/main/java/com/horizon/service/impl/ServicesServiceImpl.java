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


@Service
@AllArgsConstructor
public class ServicesServiceImpl implements ServicesService {

    private ServicesRepository servicesRepository;
    private ServicesMapper servicesMapper;


    @Override
    public ServicesDto createServices(ServicesDto servicesDto) {
        Services services = servicesMapper.mapToService(servicesDto, null);
        Services saveServices = servicesRepository.save(services);
        return servicesMapper.mapToServicesDto(saveServices);
    }


    public ServicesDto updateServices(Integer serviceId, ServicesDto servicesDto) {
        Services existingServices = servicesRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found " + serviceId));

        Services updatedServices = servicesMapper.mapToService(servicesDto, existingServices);

        updatedServices = servicesRepository.save(updatedServices);
        return servicesMapper.mapToServicesDto(updatedServices);
    }


    @Override
    public void deleteServices(Integer serviceId) {
        Services services = servicesRepository.findById(serviceId).orElseThrow(
                () -> new ResourceNotFoundException("Service not found " + serviceId)
        );
        servicesRepository.delete(services);
    }

    @Override
    public ServicesDto getServicesById(Integer serviceId) {
        Services services = servicesRepository.findById(serviceId).orElseThrow(
                () -> new ResourceNotFoundException("Services is not exist with given id: " + serviceId)
        );
        return servicesMapper.mapToServicesDto(services);
    }

    @Override
    public Page<ServicesDto> getAllServices(Pageable pageable) {
        Page<Services> servicesPage = servicesRepository.findAll(pageable);
        return servicesPage.map(servicesMapper::mapToServicesDto);
    }

    @Override
    public Page<ServicesDto> getServicesByName(String name, Pageable pageable) {
        Page<Services> servicesPage = servicesRepository.findByDescriptionContainingIgnoreCase(name, pageable);
        return servicesPage.map(servicesMapper::mapToServicesDto);
    }

    @Override
    public Page<ServicesDto> getServicesByRoomTypeName(String roomTypeName, Pageable pageable) {
        Page<Services> servicesPage = servicesRepository.findByRoomType_NameContainingIgnoreCase(roomTypeName, pageable);
        return servicesPage.map(servicesMapper::mapToServicesDto);
    }

    @Override
    public Page<ServicesDto> getServicesByRoomId(Integer roomId, Pageable pageable) {
        Page<Services> servicesPage = servicesRepository.findServicesByRoomId(roomId, pageable);
        return servicesPage.map(servicesMapper::mapToServicesDto);
    }

    @Override
    public Page<ServicesDto> getServicesByRoomName(String roomName, Pageable pageable) {
        Page<Services> servicesPage = servicesRepository.findServicesByRoomName(roomName, pageable);
        return servicesPage.map(servicesMapper::mapToServicesDto);
    }

}
