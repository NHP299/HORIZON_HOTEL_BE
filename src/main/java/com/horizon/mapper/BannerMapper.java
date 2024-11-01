package com.horizon.mapper;

import com.horizon.domain.Banner;
import com.horizon.dto.BannerDto;

import java.util.List;

public interface BannerMapper {
    BannerDto bannerToBannerDto(Banner banner);
    Banner bannerDtoToBanner(BannerDto bannerDto);
    List<BannerDto> bannerListToBannerDtoList(List<Banner> banners);
}
