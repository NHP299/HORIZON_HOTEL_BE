package com.horizon.controller.admin;

import com.horizon.dto.PromotionTypeDto;
import com.horizon.service.PromotionTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/admin/promotion-type")
@Validated
public class PromotionTypeController {
    private PromotionTypeService promotionTypeService;

    @PostMapping
    public ResponseEntity<PromotionTypeDto> create(@RequestBody PromotionTypeDto promotionTypeDto) {
        PromotionTypeDto savePromotionType = promotionTypeService.createPromotionType(promotionTypeDto);
        return new ResponseEntity<>(savePromotionType, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<PromotionTypeDto> getPromotionTypeById(@PathVariable Integer id) {
        PromotionTypeDto promotionTypeDto = promotionTypeService.getPromotionTypeById(id);
        return ResponseEntity.ok(promotionTypeDto);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<PromotionTypeDto>> getAllPromotionTypes(Pageable pageable) {
        Page<PromotionTypeDto> promotionTypeDto = promotionTypeService.getAllPromotionTypes(pageable);
        return ResponseEntity.ok(promotionTypeDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<PromotionTypeDto> update(@PathVariable("id") Integer promotionTypeId, @RequestBody PromotionTypeDto updatePromotionTypeDto) {
        PromotionTypeDto promotionTypeDto = promotionTypeService.updatePromotionType(promotionTypeId, updatePromotionTypeDto);
        return ResponseEntity.ok(promotionTypeDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer promotionTypeId) {
        promotionTypeService.deletePromotionType(promotionTypeId);
        return new ResponseEntity<>("PromotionType deleted successfully",HttpStatus.OK);
    }
}
