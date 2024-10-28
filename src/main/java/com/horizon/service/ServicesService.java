package com.horizon.service;

import com.horizon.dto.ServicesDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ServicesService {

    ServicesDto createServices(ServicesDto servicesDto);
    ServicesDto updateServices(Integer serviceId, ServicesDto servicesDto);
    void deleteServices(Integer serviceId);
    ServicesDto getServicesById(Integer serviceId);
    Page<ServicesDto> getAllServices(Pageable pageable);
    Page<ServicesDto> getServicesByName(String name, Pageable pageable);
    Page<ServicesDto> getServicesByRoomTypeName(String roomTypeName, Pageable pageable);
    Page<ServicesDto> getServicesByRoomId(Integer roomId, Pageable pageable);
    Page<ServicesDto> getServicesByRoomName(String roomName, Pageable pageable);

}
