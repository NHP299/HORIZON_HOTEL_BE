package com.horizon.controller.admin;

import com.horizon.dto.PromotionDto;
import com.horizon.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/admin/promotion")
@Validated
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

    @GetMapping("/by-id-available/{id}")
    public ResponseEntity<PromotionDto> getPromotionByIdAndAvailable(@PathVariable("id") Integer id) {
        PromotionDto promotionDto = promotionService.getPromotionByIdAndAvailable(id);
        return ResponseEntity.ok(promotionDto);
    }

    @GetMapping("/all-available")
    public ResponseEntity<Page<PromotionDto>> getAllAvailablePromotions(Pageable pageable) {
        Page<PromotionDto> promotionDto = promotionService.getAllAvailablePromotions(pageable);
        return ResponseEntity.ok(promotionDto);
    }

    @GetMapping("/by-name-available")
    public ResponseEntity<Page<PromotionDto>> getPromotionByNameAndAvailable(@RequestParam String name, Pageable pageable) {
        Page<PromotionDto> promotionDto = promotionService.getPromotionByNameAndAvailable(name,pageable);
        return ResponseEntity.ok(promotionDto);
    }

    @GetMapping("/applicable")
    public ResponseEntity<List<PromotionDto>> getApplicablePromotions(
            @RequestParam("daysOfBooking") Integer daysOfBooking,
            @RequestParam("roomsOfBooking") Integer roomsOfBooking) {
        List<PromotionDto> promotions = promotionService.getApplicablePromotions(daysOfBooking, roomsOfBooking);
        return ResponseEntity.ok(promotions);
    };

}
