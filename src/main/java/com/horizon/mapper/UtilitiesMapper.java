package com.horizon.mapper;

import com.horizon.domain.Utilities;
import com.horizon.dto.UtilitiesDto;

public interface UtilitiesMapper {
    UtilitiesDto mapToUtilitiesDto(Utilities utilities);
    Utilities mapToUtilities(UtilitiesDto utilitiesDto, Utilities existingUtilities);
}
