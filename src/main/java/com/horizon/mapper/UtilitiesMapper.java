package com.horizon.mapper;

import com.horizon.domain.RoomType;
import com.horizon.domain.Utilities;
import com.horizon.dto.UtilitiesDto;

public class UtilitiesMapper {
    public static UtilitiesDto mapToUtilitiesDto(Utilities utilities) {
        return new UtilitiesDto(utilities.getId(),
                utilities.getRoomType().getId(),
                utilities.getName(),
                utilities.getDescription()
        );
    }

    public static Utilities mapToUtilities(UtilitiesDto utilitiesDto) {
        RoomType roomType = new RoomType();
        roomType.setId(utilitiesDto.getRoomTypeId());

        return new Utilities(utilitiesDto.getId(),
                roomType,
                utilitiesDto.getName(),
                utilitiesDto.getDescription()
        );
    }

}
