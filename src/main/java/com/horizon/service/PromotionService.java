package com.horizon.service;


import com.horizon.dto.PromotionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PromotionService {
    PromotionDto createPromotion(PromotionDto promotionDto);
    PromotionDto updatePromotion(Integer promotionId,PromotionDto promotionDto);
    void deletePromotion(Integer promotionId);
    PromotionDto getPromotionById(Integer promotionId);
    Page<PromotionDto> getAllPromotions(Pageable pageable);
    Page<PromotionDto> getPromotionByName(String name, Pageable pageable);
    PromotionDto getPromotionByIdAndAvailable(Integer promotionId);
    Page<PromotionDto> getAllAvailablePromotions(Pageable pageable);
    Page<PromotionDto> getPromotionByNameAndAvailable(String name, Pageable pageable);

}
