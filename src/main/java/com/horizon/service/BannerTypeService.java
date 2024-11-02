package com.horizon.service;

import com.horizon.dto.BannerTypeDto;

import java.util.List;

public interface BannerTypeService {
    String createBannerType(BannerTypeDto bannerTypeDto);
    String updateBannerType(Integer id, BannerTypeDto bannerTypeDto);
    String deleteBannerType(Integer id);
    List<BannerTypeDto> getAllBannerTypes();
    BannerTypeDto getBannerTypeById(Integer id);
}
