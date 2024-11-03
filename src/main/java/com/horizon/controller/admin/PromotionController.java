package com.horizon.controller.admin;

import com.horizon.dto.PromotionDto;
import com.horizon.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/admin/promotion")
public class PromotionController {
    private PromotionService promotionService;

    @PostMapping
    public ResponseEntity<PromotionDto> create(@RequestBody PromotionDto promotionDto) {
        PromotionDto savePromotion = promotionService.createPromotion(promotionDto);
        return new ResponseEntity<>(savePromotion, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<PromotionDto> getPromotionById(@PathVariable Integer id) {
        PromotionDto promotionDto = promotionService.getPromotionById(id);
        return ResponseEntity.ok(promotionDto);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<PromotionDto>> getAllPromotions(Pageable pageable) {
        Page<PromotionDto> promotionDto = promotionService.getAllPromotions(pageable);
        return ResponseEntity.ok(promotionDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<PromotionDto> update(@PathVariable("id") Integer promotionId, @RequestBody PromotionDto updatePromotionDto) {
        PromotionDto promotionDto = promotionService.updatePromotion(promotionId, updatePromotionDto);
        return ResponseEntity.ok(promotionDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer promotionId) {
        promotionService.deletePromotion(promotionId);
        return new ResponseEntity<>("Promotion deleted successfully",HttpStatus.OK);
    }

    @GetMapping("/by-id-and-time-range/{id}")
    public ResponseEntity<PromotionDto> getPromotionByIdAndTimeRange(@PathVariable Integer id) {
        PromotionDto promotionDto = promotionService.getPromotionByIdAndTimeRange(id);
        return ResponseEntity.ok(promotionDto);
    }

    @GetMapping("/all-within-time-range")
    public ResponseEntity<Page<PromotionDto>> getAllPromotionsAndTimeRange(Pageable pageable) {
        Page<PromotionDto> promotionDto = promotionService.getAllPromotionsWithinTimeRange(pageable);
        return ResponseEntity.ok(promotionDto);
    }

}
