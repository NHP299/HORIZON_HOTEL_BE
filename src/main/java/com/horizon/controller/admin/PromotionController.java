package com.horizon.controller.admin;

import com.horizon.dto.PromotionDto;
import com.horizon.service.PromotionService;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<List<PromotionDto>> getAllPromotions() {
        List<PromotionDto> promotionDto = promotionService.getAllPromotions();
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
    public ResponseEntity<List<PromotionDto>> getAllAvailablePromotions() {
        List<PromotionDto> promotionDto = promotionService.getAllAvailablePromotions();
        return ResponseEntity.ok(promotionDto);
    }

    @GetMapping("/by-name-available")
    public ResponseEntity<List<PromotionDto>> getPromotionByNameAndAvailable(@RequestParam String name ) {
        List<PromotionDto> promotionDto = promotionService.getPromotionByNameAndAvailable(name);
        return ResponseEntity.ok(promotionDto);
    }

    @GetMapping("/applicable")
    public ResponseEntity<List<PromotionDto>> getApplicablePromotions(
            @RequestParam("days_of_booking") Integer daysOfBooking,
            @RequestParam("rooms_of_booking") Integer roomsOfBooking) {
        List<PromotionDto> promotions = promotionService.getApplicablePromotions(daysOfBooking, roomsOfBooking);
        return ResponseEntity.ok(promotions);
    };

}
