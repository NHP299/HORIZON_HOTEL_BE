package com.horizon.mapper;

import com.horizon.domain.RoomType;
import com.horizon.domain.Services;
import com.horizon.dto.ServicesDto;

public class ServicesMapper {
    public static ServicesDto mapToServicesDto(Services services) {
        return new ServicesDto(services.getId(),
                services.getRoomType().getId(),
                services.getDescription(),
                services.getStartedTime(),
                services.getEndTime()
        );
    }

    public static Services mapToService(ServicesDto servicesDto) {
        RoomType roomType = new RoomType();
        roomType.setId(servicesDto.getRoomTypeId());

        return new Services(servicesDto.getId(),
                roomType,
                servicesDto.getDescription(),
                servicesDto.getStartedTime(),
                servicesDto.getEndTime());
    }

}
