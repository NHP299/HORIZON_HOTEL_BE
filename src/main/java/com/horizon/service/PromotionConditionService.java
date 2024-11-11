package com.horizon.service;

import com.horizon.dto.PromotionConditionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PromotionConditionService {
    PromotionConditionDto createPromotionCondition(PromotionConditionDto promotionConditionDto);
    PromotionConditionDto updatePromotionCondition(Integer promotionConditionId, PromotionConditionDto promotionConditionDto);
    void deletePromotionCondition(Integer promotionConditionId);
    PromotionConditionDto getPromotionConditionById(Integer promotionConditionId);
    Page<PromotionConditionDto> getAllPromotionConditions(Pageable pageable);
}
