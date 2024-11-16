package com.horizon.service;


import com.horizon.dto.PromotionDto;

import java.util.List;

public interface PromotionService {
    PromotionDto createPromotion(PromotionDto promotionDto);
    PromotionDto updatePromotion(Integer promotionId,PromotionDto promotionDto);
    void deletePromotion(Integer promotionId);
    PromotionDto getPromotionById(Integer promotionId);
    List<PromotionDto> getAllPromotions();
    List<PromotionDto> getPromotionByName(String name);
    PromotionDto getPromotionByIdAndAvailable(Integer promotionId);
    List<PromotionDto> getAllAvailablePromotions();
    List<PromotionDto> getPromotionByNameAndAvailable(String name);
    List<PromotionDto> getApplicablePromotions(Integer daysOfBooking, Integer roomsOfBooking);

}
