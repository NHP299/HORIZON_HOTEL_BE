package com.horizon.controller.admin;

import com.horizon.dto.BannerTypeDto;
import com.horizon.service.BannerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/banner-types")
public class BannerTypeController {

    private final BannerTypeService bannerTypeService;

    @Autowired
    public BannerTypeController(BannerTypeService bannerTypeService) {
        this.bannerTypeService = bannerTypeService;
    }

    @PostMapping
    public ResponseEntity<String> createBannerType(@RequestBody BannerTypeDto bannerTypeDto) {
        String message = bannerTypeService.createBannerType(bannerTypeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBannerType(@PathVariable Integer id, @RequestBody BannerTypeDto bannerTypeDto) {
        String message = bannerTypeService.updateBannerType(id, bannerTypeDto);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBannerType(@PathVariable Integer id) {
        String message = bannerTypeService.deleteBannerType(id);
        return ResponseEntity.ok(message);
    }

    @GetMapping
    public ResponseEntity<List<BannerTypeDto>> getAllBannerTypes() {
        List<BannerTypeDto> bannerTypes = bannerTypeService.getAllBannerTypes();
        return ResponseEntity.ok(bannerTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BannerTypeDto> getBannerTypeById(@PathVariable Integer id) {
        BannerTypeDto bannerType = bannerTypeService.getBannerTypeById(id);
        return ResponseEntity.ok(bannerType);
    }
}
