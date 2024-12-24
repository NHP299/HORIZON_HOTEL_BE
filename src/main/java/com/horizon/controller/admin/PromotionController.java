package com.horizon.controller.admin;

import com.horizon.dto.PromotionDto;
import com.horizon.response.ResponseObject;
import com.horizon.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-admin}/promotion")
public class PromotionController {
    private PromotionService promotionService;

    @PostMapping
    public ResponseObject<?> create(@RequestBody PromotionDto promotionDto) {
        PromotionDto savePromotion = promotionService.create(promotionDto);
        if (savePromotion == null) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Addition failed!", null);
        }
        return new ResponseObject<>(HttpStatus.CREATED, "Added successfully!", savePromotion);
    }

    @GetMapping("{id}")
    public ResponseEntity<PromotionDto> getById(@PathVariable("id") Integer promotionId) {
        PromotionDto promotionDto = promotionService.getById(promotionId);
        return ResponseEntity.ok(promotionDto);
    }

    @GetMapping("/by-name")
    public ResponseEntity<List<PromotionDto>> getByName(@RequestParam String name ) {
        List<PromotionDto> promotionDto = promotionService.getByName(name);
        return ResponseEntity.ok(promotionDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PromotionDto>> getAll() {
        List<PromotionDto> promotionDto = promotionService.getAll();
        return ResponseEntity.ok(promotionDto);
    }

    @PutMapping("{id}")
    public ResponseObject<?> update(@PathVariable("id") Integer promotionId, @RequestBody PromotionDto updatePromotionDto) {
        PromotionDto promotionDto = promotionService.update(promotionId, updatePromotionDto);
        if (promotionDto == null) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Update failed!", null);
        }
        return new ResponseObject<>(HttpStatus.OK, "Updated successfully!", promotionDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer promotionId) {
        promotionService.delete(promotionId);
        return new ResponseEntity<>("Promotion deleted successfully",HttpStatus.OK);
    }

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

}
