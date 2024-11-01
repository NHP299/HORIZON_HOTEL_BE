package com.horizon.service;

import com.horizon.dto.BannerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface BannerService {

    ResponseEntity<Map<String, Object>> createNewBanner(MultipartFile file, BannerDto bannerDto);

    ResponseEntity<Map<String, Object>> updateBanner(Integer bannerId, MultipartFile file, BannerDto bannerDto);

    ResponseEntity<Map<String, Object>> deleteBanner(Integer bannerId);

    BannerDto getBannerById(Integer bannerId);

    List<BannerDto> getAllBanners();
}
