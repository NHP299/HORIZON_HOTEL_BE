package com.horizon.controller.admin;

import com.horizon.dto.PromotionConditionDto;
import com.horizon.service.PromotionConditionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/admin/promotion-condition")
@Validated
public class PromotionConditionController {
    private PromotionConditionService promotionConditionService;

    @PostMapping
    public ResponseEntity<PromotionConditionDto> create(@RequestBody PromotionConditionDto promotionConditionDto) {
        PromotionConditionDto savePromotionCondition = promotionConditionService.create(promotionConditionDto);
        return new ResponseEntity<>(savePromotionCondition, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<PromotionConditionDto> getById(@PathVariable Integer id) {
        PromotionConditionDto promotionConditionDto = promotionConditionService.getById(id);
        return ResponseEntity.ok(promotionConditionDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PromotionConditionDto>> getAll() {
        List<PromotionConditionDto> promotionConditionDto = promotionConditionService.getAll();
        return ResponseEntity.ok(promotionConditionDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<PromotionConditionDto> update(@PathVariable("id") Integer promotionConditionId, @RequestBody PromotionConditionDto updatePromotionConditionDto) {
        PromotionConditionDto promotionConditionDto = promotionConditionService.update(promotionConditionId, updatePromotionConditionDto);
        return ResponseEntity.ok(promotionConditionDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer promotionConditionId) {
        promotionConditionService.delete(promotionConditionId);
        return new ResponseEntity<>("PromotionCondition deleted successfully",HttpStatus.OK);
    }
}
