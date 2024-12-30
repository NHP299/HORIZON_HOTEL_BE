package com.horizon.controller.admin;

import com.horizon.dto.PromotionDto;
import com.horizon.response.ResponseObject;
import com.horizon.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController("adminController")
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-admin}/promotion")
public class PromotionController {
    private PromotionService promotionService;

    @PostMapping
    public ResponseObject<?> create(@RequestBody PromotionDto promotionDto) {
        try {
            PromotionDto savePromotion = promotionService.create(promotionDto);
            return new ResponseObject<>(HttpStatus.CREATED, "Added successfully!", savePromotion);
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseObject<?> update(@PathVariable("id") Integer promotionId, @RequestBody PromotionDto updatePromotionDto) {
        try {
            PromotionDto promotionDto = promotionService.update(promotionId, updatePromotionDto);
            return new ResponseObject<>(HttpStatus.OK, "Updated successfully!", promotionDto);
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseObject<?> delete(@PathVariable("id") Integer promotionId) {
        try {
            promotionService.delete(promotionId);
            return new ResponseObject<>(HttpStatus.OK, "Deleted successfully!", null);
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseObject<?> getById(@PathVariable("id") Integer promotionId) {
        try {
            return new ResponseObject<>(HttpStatus.OK, "Success", promotionService.getById(promotionId));
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @GetMapping("/by-name")
    public ResponseObject<?> getByName(@RequestParam String name ) {
        try {
            return new ResponseObject<>(HttpStatus.OK, "Success", promotionService.getByName(name));
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseObject<?> getAll() {
        try {
            return new ResponseObject<>(HttpStatus.OK, "Success", promotionService.getAll());
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

}
