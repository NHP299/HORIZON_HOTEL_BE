package com.horizon.mapper.impl;

import com.horizon.domain.Banner;
import com.horizon.dto.BannerDto;
import com.horizon.mapper.BannerMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BannerMapperImpl implements BannerMapper {

    @Override
    public BannerDto bannerToBannerDto(Banner banner) {
        if (banner == null) {
            return null;
        }

        BannerDto bannerDto = new BannerDto();
        bannerDto.setId(banner.getId());
        bannerDto.setName(banner.getName());
        bannerDto.setDescription(banner.getDescription());
        bannerDto.setPath(banner.getPath());
        bannerDto.setBannerTypeId(banner.getBannerType().getId());
        return bannerDto;
    }

    @Override
    public Banner bannerDtoToBanner(BannerDto bannerDto) {
        if (bannerDto == null) {
            return null;
        }

        Banner banner = new Banner();
        banner.setId(bannerDto.getId());
        banner.setName(bannerDto.getName());
        banner.setDescription(bannerDto.getDescription());
        banner.setPath(bannerDto.getPath());
        return banner;
    }

    @Override
    public List<BannerDto> bannerListToBannerDtoList(List<Banner> banners) {
        if (banners == null) {
            return null;
        }

        return banners.stream()
                .map(this::bannerToBannerDto)
                .collect(Collectors.toList());
    }
}
