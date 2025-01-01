package com.horizon.service;

import com.horizon.dto.ServicesDto;
import java.util.List;

public interface ServicesService {

    ServicesDto create(ServicesDto servicesDto);
    ServicesDto update(Integer serviceId, ServicesDto servicesDto);
    void delete(Integer serviceId);
    ServicesDto getById(Integer serviceId);
    List<ServicesDto> getAll();
    List<ServicesDto> getByName(String name);

}
