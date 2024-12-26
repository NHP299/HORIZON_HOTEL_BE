package com.horizon.controller.home;

import com.horizon.dto.PromotionDto;
import com.horizon.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController("homeController")
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-home}/promotion")
public class PromotionController {
    private PromotionService promotionService;

    @GetMapping("available-by-id/{id}")
    public ResponseEntity<PromotionDto> getAvailableById(@PathVariable("id") Integer promotionId) {
        PromotionDto promotionDto = promotionService.getAvailableById(promotionId);
        return ResponseEntity.ok(promotionDto);
    }

    @GetMapping("/all-available-{id}")
    public ResponseEntity<List<PromotionDto>> getAllAvailable(@PathVariable("id")Integer roomTypeId) {
        List<PromotionDto> promotionDto = promotionService.getAllAvailable(roomTypeId);
        return ResponseEntity.ok(promotionDto);
    }

    @PostMapping("apply/{id}")
    public ResponseEntity<Double> apply(@PathVariable("id") Integer promotionId, @RequestParam Double totalPrice) {
        Double discountedPrice = promotionService.apply(promotionId, totalPrice);
        return ResponseEntity.ok(discountedPrice);
    }
}
