package com.horizon.mapper;

import com.horizon.domain.Services;
import com.horizon.dto.ServicesDto;

public interface ServicesMapper {
    ServicesDto mapToServicesDto(Services services);
    Services mapToService(ServicesDto servicesDto, Services existingServices);

}
