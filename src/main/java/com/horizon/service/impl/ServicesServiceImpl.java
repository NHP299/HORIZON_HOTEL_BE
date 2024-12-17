package com.horizon.service.impl;

import com.horizon.domain.Services;
import com.horizon.dto.ServicesDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.ServicesMapper;
import com.horizon.repository.ServicesRepository;
import com.horizon.service.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ServicesServiceImpl implements ServicesService {

    private ServicesRepository servicesRepository;
    private ServicesMapper servicesMapper;


    @Override
    public ServicesDto create(ServicesDto servicesDto) {
        Services services = servicesMapper.mapToService(servicesDto, null);
        Services saveServices = servicesRepository.save(services);
        return servicesMapper.mapToServicesDto(saveServices);
    }

    @Override
    public ServicesDto update(Integer serviceId, ServicesDto servicesDto) {
        Services existingServices = servicesRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found " + serviceId));

        Services updatedServices = servicesMapper.mapToService(servicesDto, existingServices);

        updatedServices = servicesRepository.save(updatedServices);
        return servicesMapper.mapToServicesDto(updatedServices);
    }


    @Override
    public void delete(Integer serviceId) {
        Services services = servicesRepository.findById(serviceId).orElseThrow(
                () -> new ResourceNotFoundException("Service not found " + serviceId)
        );
        servicesRepository.delete(services);
    }

    @Override
    public ServicesDto getById(Integer serviceId) {
        Services services = servicesRepository.findById(serviceId).orElseThrow(
                () -> new ResourceNotFoundException("Services is not exist with given id: " + serviceId)
        );
        return servicesMapper.mapToServicesDto(services);
    }

    @Override
    public List<ServicesDto> getAll() {
        List<Services> servicesPage = servicesRepository.findAll();
        return servicesPage.stream().map(servicesMapper::mapToServicesDto).toList();
    }

    @Override
    public List<ServicesDto> getByName(String name) {
        List<Services> servicesPage = servicesRepository.findByDescriptionContainingIgnoreCase(name);
        return servicesPage.stream().map(servicesMapper::mapToServicesDto).toList();
    }

    @Override
    public List<ServicesDto> getByRoomTypeName(String roomTypeName) {
        List<Services> servicesPage = servicesRepository.findByRoomType_NameContainingIgnoreCase(roomTypeName);
        return servicesPage.stream().map(servicesMapper::mapToServicesDto).toList();
    }

    @Override
    public List<ServicesDto> getByRoomId(Integer roomId) {
        List<Services> servicesPage = servicesRepository.findByRoomId(roomId);
        return servicesPage.stream().map(servicesMapper::mapToServicesDto).toList();
    }

    @Override
    public List<ServicesDto> getByRoomName(String roomName) {
        List<Services> servicesPage = servicesRepository.findByRoomName(roomName);
        return servicesPage.stream().map(servicesMapper::mapToServicesDto).toList();
    }

}
