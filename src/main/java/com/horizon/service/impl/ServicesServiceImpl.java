package com.horizon.service.impl;

import com.horizon.domain.RoomType;
import com.horizon.domain.Services;
import com.horizon.dto.ServicesDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.ServicesMapper;
import com.horizon.repository.RoomTypeRepository;
import com.horizon.repository.ServicesRepository;
import com.horizon.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepository servicesRepository;
    private final RoomTypeRepository roomTypeRepository;

    @Autowired
    public ServicesServiceImpl(ServicesRepository servicesRepository, RoomTypeRepository roomTypeRepository) {
        this.servicesRepository = servicesRepository;
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public ServicesDto createServices(ServicesDto servicesDto) {
        Services services = ServicesMapper.mapToService(servicesDto);
        Services saveServices = servicesRepository.save(services);
        return ServicesMapper.mapToServicesDto(saveServices);
    }


    @Override
    public ServicesDto updateServices(Integer serviceId, ServicesDto updateServices) {
        Services services = servicesRepository.findById(serviceId).orElseThrow(
                () -> new ResourceNotFoundException("Services is not exist with given id: " + serviceId));
        RoomType roomType = roomTypeRepository.findById(updateServices.getRoomTypeId()).orElseThrow(
                () -> new ResourceNotFoundException("RoomType is not exist with given id: " + updateServices.getRoomTypeId()));
        services.setRoomType(roomType);
        services.setDescription(updateServices.getDescription());
        services.setStartedTime(updateServices.getStartedTime());
        services.setEndTime(updateServices.getEndTime());

        Services updateServicesObj = servicesRepository.save(services);
        return ServicesMapper.mapToServicesDto(updateServicesObj);
    }

    @Override
    public void deleteServices(Integer serviceId) {
        Services services = servicesRepository.findById(serviceId).orElseThrow(
                () -> new ResourceNotFoundException("Services is not exist with given id: " + serviceId)
        );
        servicesRepository.delete(services);
    }

    @Override
    public ServicesDto getServicesById(Integer serviceId) {
        Services services = servicesRepository.findById(serviceId).orElseThrow(
                () -> new ResourceNotFoundException("Services is not exist with given id: " + serviceId)
        );
        return ServicesMapper.mapToServicesDto(services);
    }

    @Override
    public List<ServicesDto> getAllServices() {
        List<Services> services = servicesRepository.findAll();
        return services.stream().map((service) -> ServicesMapper.mapToServicesDto(service)).collect(Collectors.toList());
    }

}
