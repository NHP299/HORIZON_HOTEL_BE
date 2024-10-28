package com.horizon.service.impl;

import com.horizon.repository.BannerTypeRepository;
import com.horizon.service.BannerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BannerTypeServiceImpl implements BannerTypeService {
    private final BannerTypeRepository bannerTypeRepository;

    @Autowired
    private BannerTypeServiceImpl(BannerTypeRepository bannerTypeRepository) {
        this.bannerTypeRepository = bannerTypeRepository;
    }


}
