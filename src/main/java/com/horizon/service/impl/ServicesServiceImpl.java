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
        Services services = servicesMapper.mapToService(servicesDto);
        Services saveServices = servicesRepository.save(services);
        return servicesMapper.mapToServicesDto(saveServices);
    }

    @Override
    public ServicesDto update(Integer serviceId, ServicesDto servicesDto) {
        Services updatedServices = servicesRepository.findById(serviceId)
                .map(existingServices -> {
                    existingServices = servicesMapper.mapToService(servicesDto);
                    existingServices.setId(serviceId);
                    return servicesRepository.save(existingServices);
                }).orElseThrow(() -> new ResourceNotFoundException("Service not found " + serviceId));
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
        List<Services> servicesPage = servicesRepository.findByNameContainingIgnoreCase(name);
        return servicesPage.stream().map(servicesMapper::mapToServicesDto).toList();
    }

}
