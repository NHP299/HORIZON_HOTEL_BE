package com.horizon.mapper;

import com.horizon.domain.Media;
import com.horizon.dto.MediaDto;

public interface MediaMapper {
    MediaDto toDto(Media media);
    Media toEntity(MediaDto mediaDto);
}

