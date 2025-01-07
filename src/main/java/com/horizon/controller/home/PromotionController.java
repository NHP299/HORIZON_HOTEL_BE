package com.horizon.controller.home;

import com.horizon.response.ResponseObject;
import com.horizon.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController("HomePromotionController")
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-home}/promotion")
public class PromotionController {
    private PromotionService promotionService;

    @GetMapping("available-by-id/{id}")
    public ResponseObject<?> getAvailableById(@PathVariable("id") Integer promotionId) {
        try {
            return new ResponseObject<>(HttpStatus.OK, "Success", promotionService.getAvailableById(promotionId));
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @GetMapping("/all-available-and-roomType/{id}")
    public ResponseObject<?> getAllAvailable(@PathVariable("id")Integer roomTypeId, Pageable pageable) {
        try {
            return new ResponseObject<>(HttpStatus.OK, "Success", promotionService.getAllAvailable(roomTypeId, pageable));
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @PostMapping("apply/{id}")
    public ResponseObject<?> apply(@PathVariable("id") Integer promotionId, @RequestParam Double totalPrice) {
        try {
            Double discountedPrice = promotionService.apply(promotionId, totalPrice);
            return new ResponseObject<>(HttpStatus.OK, "Success", discountedPrice);
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }
}
