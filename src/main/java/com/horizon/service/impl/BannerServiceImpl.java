package com.horizon.service.impl;

import com.horizon.repository.BannerRepository;
import com.horizon.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;

    @Autowired
    private BannerServiceImpl(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

}
