package com.horizon.service;

import com.horizon.dto.BannerTypeDto;

import java.util.List;

public interface BannerTypeService {
    BannerTypeDto createBannerType(BannerTypeDto bannerTypeDto);
    BannerTypeDto updateBannerType(Integer id, BannerTypeDto bannerTypeDto);
    void deleteBannerType(Integer id);
    List<BannerTypeDto> getAllBannerTypes();
    BannerTypeDto getBannerTypeById(Integer id);
}
