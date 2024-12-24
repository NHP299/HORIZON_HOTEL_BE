package com.horizon.mapper.impl;

import com.horizon.domain.Utilities;
import com.horizon.dto.UtilitiesDto;
import com.horizon.mapper.UtilitiesMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UtilitiesMapperImpl implements UtilitiesMapper {

    @Override
    public UtilitiesDto mapToUtilitiesDto(Utilities utilities) {
        if (utilities == null) {
            return null;
        }
        UtilitiesDto utilitiesDto = new UtilitiesDto();
        utilitiesDto.setId(utilities.getId());
        utilitiesDto.setName(utilities.getName());
        utilitiesDto.setDescription(utilities.getDescription());
        return utilitiesDto;
    }

    @Override
    public Utilities mapToUtilities(UtilitiesDto utilitiesDto) {
        if (utilitiesDto == null) {
            return null;
        }
        Utilities utilities = new Utilities();
        utilities.setId(utilitiesDto.getId());
        utilities.setName(utilitiesDto.getName());
        utilities.setDescription(utilitiesDto.getDescription());
        return utilities;
    }
}
