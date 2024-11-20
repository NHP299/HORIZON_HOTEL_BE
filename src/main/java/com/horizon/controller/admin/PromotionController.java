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
        PromotionDto savePromotion = promotionService.create(promotionDto);
        return new ResponseEntity<>(savePromotion, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<PromotionDto> getById(@PathVariable("id") Integer promotionId) {
        PromotionDto promotionDto = promotionService.getById(promotionId);
        return ResponseEntity.ok(promotionDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PromotionDto>> getAll() {
        List<PromotionDto> promotionDto = promotionService.getAll();
        return ResponseEntity.ok(promotionDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<PromotionDto> update(@PathVariable("id") Integer promotionId, @RequestBody PromotionDto updatePromotionDto) {
        PromotionDto promotionDto = promotionService.update(promotionId, updatePromotionDto);
        return ResponseEntity.ok(promotionDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer promotionId) {
        promotionService.delete(promotionId);
        return new ResponseEntity<>("Promotion deleted successfully",HttpStatus.OK);
    }

    @GetMapping("/by-id-available/{id}")
    public ResponseEntity<PromotionDto> getByIdAndAvailable(@PathVariable("id") Integer promotionId) {
        PromotionDto promotionDto = promotionService.getByIdAndAvailable(promotionId);
        return ResponseEntity.ok(promotionDto);
    }

    @GetMapping("/all-available")
    public ResponseEntity<List<PromotionDto>> getAllAvailable() {
        List<PromotionDto> promotionDto = promotionService.getAllAvailable();
        return ResponseEntity.ok(promotionDto);
    }

    @GetMapping("/by-name-available")
    public ResponseEntity<List<PromotionDto>> getByNameAndAvailable(@RequestParam String name ) {
        List<PromotionDto> promotionDto = promotionService.getByNameAndAvailable(name);
        return ResponseEntity.ok(promotionDto);
    }

    @GetMapping("/applicable")
    public ResponseEntity<List<PromotionDto>> getApplicable(
            @RequestParam("days_of_booking") Integer daysOfBooking,
            @RequestParam("rooms_of_booking") Integer roomsOfBooking) {
        List<PromotionDto> promotions = promotionService.getApplicable(daysOfBooking, roomsOfBooking);
        return ResponseEntity.ok(promotions);
    }

    @PostMapping("/apply/{id}")
    public ResponseEntity<String> apply(@PathVariable("id") Integer promotionId) {
        promotionService.apply(promotionId);
        return ResponseEntity.ok("Promotion applied successfully.");
    }

}
