package com.horizon.service;

import com.horizon.dto.ServicesDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ServicesService {

    ServicesDto create(ServicesDto servicesDto);
    ServicesDto update(Integer serviceId, ServicesDto servicesDto);
    void delete(Integer serviceId);
    ServicesDto getById(Integer serviceId);
    Page<ServicesDto> getAll(Pageable pageable);
    Page<ServicesDto> getByName(String name, Pageable pageable);
    Page<ServicesDto> getByRoomTypeName(String roomTypeName, Pageable pageable);
    Page<ServicesDto> getByRoomId(Integer roomId, Pageable pageable);
    Page<ServicesDto> getByRoomName(String roomName, Pageable pageable);

}
