package com.horizon.mapper;

import com.horizon.domain.Promotion;
import com.horizon.dto.PromotionDto;

public interface PromotionMapper {
    PromotionDto maptoPromotionDto(Promotion promotion);
    Promotion maptoPromotion(PromotionDto promotionDto, Promotion existingPromotion);
}
