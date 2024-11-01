package com.horizon.controller.admin;

import com.horizon.dto.BannerDto;
import com.horizon.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/banners")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> createNewBanner(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("bannerTypeId") Integer bannerTypeId) {

        BannerDto bannerDto = new BannerDto();
        bannerDto.setName(name);
        bannerDto.setDescription(description);
        bannerDto.setBannerTypeId(bannerTypeId);

        return bannerService.createNewBanner(file, bannerDto);
    }

    @PutMapping("/update/{bannerId}")
    public ResponseEntity<Map<String, Object>> updateBanner(
            @PathVariable Integer bannerId,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("bannerTypeId") Integer bannerTypeId) {

        BannerDto bannerDto = new BannerDto();
        bannerDto.setName(name);
        bannerDto.setDescription(description);
        bannerDto.setBannerTypeId(bannerTypeId);

        return bannerService.updateBanner(bannerId, file, bannerDto);
    }

    @DeleteMapping("/{bannerId}")
    public ResponseEntity<Map<String, Object>> deleteBanner(@PathVariable Integer bannerId) {
        return bannerService.deleteBanner(bannerId);
    }

    @GetMapping("/{bannerId}")
    public ResponseEntity<BannerDto> getBannerById(@PathVariable Integer bannerId) {
        BannerDto bannerDto = bannerService.getBannerById(bannerId);
        return ResponseEntity.ok(bannerDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BannerDto>> getAllBanners() {
        List<BannerDto> bannerList = bannerService.getAllBanners();
        return ResponseEntity.ok(bannerList);
    }
}
