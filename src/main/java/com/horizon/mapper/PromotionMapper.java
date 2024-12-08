package com.horizon.mapper;

import com.horizon.domain.Promotion;
import com.horizon.dto.PromotionDto;

public interface PromotionMapper {
    PromotionDto toPromotionDto(Promotion promotion);
    Promotion toPromotion(PromotionDto promotionDto);
}
