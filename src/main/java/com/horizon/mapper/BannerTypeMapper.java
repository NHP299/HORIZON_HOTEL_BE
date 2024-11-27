package com.horizon.mapper;

import com.horizon.domain.BannerType;
import com.horizon.dto.BannerTypeDto;

public interface BannerTypeMapper {
    BannerTypeDto toDto(BannerType bannerType);
    BannerType toEntity(BannerTypeDto bannerTypeDto);
}
