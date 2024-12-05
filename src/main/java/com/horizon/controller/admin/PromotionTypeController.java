package com.horizon.controller.admin;

import com.horizon.dto.PromotionTypeDto;
import com.horizon.service.PromotionTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/admin/promotion-type")
@Validated
public class PromotionTypeController {
    private PromotionTypeService promotionTypeService;

    @PostMapping
    public ResponseEntity<PromotionTypeDto> create(@RequestBody PromotionTypeDto promotionTypeDto) {
        PromotionTypeDto savePromotionType = promotionTypeService.create(promotionTypeDto);
        return new ResponseEntity<>(savePromotionType, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<PromotionTypeDto> getById(@PathVariable Integer id) {
        PromotionTypeDto promotionTypeDto = promotionTypeService.getById(id);
        return ResponseEntity.ok(promotionTypeDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PromotionTypeDto>> getAll() {
        List<PromotionTypeDto> promotionTypeDto = promotionTypeService.getAll();
        return ResponseEntity.ok(promotionTypeDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<PromotionTypeDto> update(@PathVariable("id") Integer promotionTypeId, @RequestBody PromotionTypeDto updatePromotionTypeDto) {
        PromotionTypeDto promotionTypeDto = promotionTypeService.update(promotionTypeId, updatePromotionTypeDto);
        return ResponseEntity.ok(promotionTypeDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer promotionTypeId) {
        promotionTypeService.delete(promotionTypeId);
        return new ResponseEntity<>("PromotionType deleted successfully",HttpStatus.OK);
    }

    @GetMapping("/by-promotion-id/{id}")
    public ResponseEntity<List<PromotionTypeDto>> getByPromotionId(@PathVariable("id") Integer promotionId) {
        List<PromotionTypeDto> promotionTypeDto = promotionTypeService.findByPromotionId(promotionId);
        return ResponseEntity.ok(promotionTypeDto);
    }
}
