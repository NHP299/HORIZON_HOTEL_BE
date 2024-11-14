package com.horizon.controller.admin;

import com.horizon.dto.PromotionConditionDto;
import com.horizon.service.PromotionConditionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/admin/promotion-condition")
@Validated
public class PromotionConditionController {
    private PromotionConditionService promotionConditionService;

    @PostMapping
    public ResponseEntity<PromotionConditionDto> create(@RequestBody PromotionConditionDto promotionConditionDto) {
        PromotionConditionDto savePromotionCondition = promotionConditionService.createPromotionCondition(promotionConditionDto);
        return new ResponseEntity<>(savePromotionCondition, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<PromotionConditionDto> getPromotionConditionById(@PathVariable Integer id) {
        PromotionConditionDto promotionConditionDto = promotionConditionService.getPromotionConditionById(id);
        return ResponseEntity.ok(promotionConditionDto);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<PromotionConditionDto>> getAllPromotionConditions(Pageable pageable) {
        Page<PromotionConditionDto> promotionConditionDto = promotionConditionService.getAllPromotionConditions(pageable);
        return ResponseEntity.ok(promotionConditionDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<PromotionConditionDto> update(@PathVariable("id") Integer promotionConditionId, @RequestBody PromotionConditionDto updatePromotionConditionDto) {
        PromotionConditionDto promotionConditionDto = promotionConditionService.updatePromotionCondition(promotionConditionId, updatePromotionConditionDto);
        return ResponseEntity.ok(promotionConditionDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer promotionConditionId) {
        promotionConditionService.deletePromotionCondition(promotionConditionId);
        return new ResponseEntity<>("PromotionCondition deleted successfully",HttpStatus.OK);
    }
}
