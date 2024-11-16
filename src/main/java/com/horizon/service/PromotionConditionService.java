package com.horizon.service;

import com.horizon.dto.PromotionConditionDto;

import java.util.List;

public interface PromotionConditionService {
    PromotionConditionDto createPromotionCondition(PromotionConditionDto promotionConditionDto);
    PromotionConditionDto updatePromotionCondition(Integer promotionConditionId, PromotionConditionDto promotionConditionDto);
    void deletePromotionCondition(Integer promotionConditionId);
    PromotionConditionDto getPromotionConditionById(Integer promotionConditionId);
    List<PromotionConditionDto> getAllPromotionConditions();
}
