package com.horizon.mapper;

import com.horizon.domain.PromotionCondition;
import com.horizon.dto.PromotionConditionDto;

public interface PromotionConditionMapper {
    PromotionConditionDto maptoPromotionConditionDto(PromotionCondition promotionCondition);
    PromotionCondition maptoPromotionCondition(PromotionConditionDto promotionConditionDto, PromotionCondition existingPromotionCondition);
}
