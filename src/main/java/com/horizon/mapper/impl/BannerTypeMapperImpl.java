package com.horizon.mapper.impl;

import com.horizon.domain.BannerType;
import com.horizon.dto.BannerTypeDto;
import com.horizon.mapper.BannerTypeMapper;
import org.springframework.stereotype.Component;

@Component
public class BannerTypeMapperImpl implements BannerTypeMapper {

    @Override
    public BannerTypeDto toDto(BannerType bannerType) {
        if (bannerType == null) {
            return null;
        }
        return new BannerTypeDto(bannerType.getId(), bannerType.getName());
    }

    @Override
    public BannerType toEntity(BannerTypeDto bannerTypeDto) {
        if (bannerTypeDto == null) {
            return null;
        }
        return new BannerType(bannerTypeDto.getId(), bannerTypeDto.getName());
    }
}
