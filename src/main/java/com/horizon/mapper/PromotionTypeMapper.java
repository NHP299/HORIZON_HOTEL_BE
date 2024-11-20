package com.horizon.mapper;

import com.horizon.domain.PromotionType;
import com.horizon.dto.PromotionTypeDto;

public interface PromotionTypeMapper {
    PromotionTypeDto maptoPromotionTypeDto(PromotionType promotionType);
    PromotionType maptoPromotionType(PromotionTypeDto promotionTypeDto, PromotionType existingPromotionType);
}
