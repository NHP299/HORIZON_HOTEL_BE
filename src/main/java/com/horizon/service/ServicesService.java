package com.horizon.service;

import com.horizon.domain.Services;
import com.horizon.dto.ServicesDto;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface ServicesService {

    ServicesDto createServices(ServicesDto servicesDto);
    ServicesDto updateServices(Integer serviceId, ServicesDto updateServices);
    void deleteServices(Integer serviceId);
    ServicesDto getServicesById(Integer serviceId);
    List<ServicesDto> getAllServices();

}
