package com.horizon.controller.admin;

import com.horizon.dto.BannerTypeDto;
import com.horizon.service.BannerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/banner-types")
public class BannerTypeController {

    private final BannerTypeService bannerTypeService;

    @Autowired
    public BannerTypeController(BannerTypeService bannerTypeService) {
        this.bannerTypeService = bannerTypeService;
    }

    @PostMapping
    public ResponseEntity<BannerTypeDto> create(@RequestBody BannerTypeDto bannerTypeDto) {
        BannerTypeDto createdBannerType = bannerTypeService.createBannerType(bannerTypeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBannerType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BannerTypeDto> update(@PathVariable Integer id, @RequestBody BannerTypeDto bannerTypeDto) {
        BannerTypeDto updatedBannerType = bannerTypeService.updateBannerType(id, bannerTypeDto);
        return ResponseEntity.ok(updatedBannerType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        bannerTypeService.deleteBannerType(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<BannerTypeDto>> getAll() {
        List<BannerTypeDto> bannerTypes = bannerTypeService.getAllBannerTypes();
        return ResponseEntity.ok(bannerTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BannerTypeDto> getById(@PathVariable Integer id) {
        BannerTypeDto bannerType = bannerTypeService.getBannerTypeById(id);
        return ResponseEntity.ok(bannerType);
    }
}
