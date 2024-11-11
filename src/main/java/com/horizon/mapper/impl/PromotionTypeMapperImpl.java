package com.horizon.mapper.impl;

import com.horizon.domain.PromotionType;
import com.horizon.dto.PromotionTypeDto;
import com.horizon.mapper.PromotionTypeMapper;
import org.springframework.stereotype.Service;

@Service
public class PromotionTypeMapperImpl implements PromotionTypeMapper {
    @Override
    public PromotionTypeDto maptoPromotionTypeDto(PromotionType promotionType) {
        if (promotionType == null) {
            return null;
        }
        PromotionTypeDto promotionTypeDto = new PromotionTypeDto();
        promotionTypeDto.setId(promotionType.getId());
        promotionTypeDto.setName(promotionType.getName());
        promotionTypeDto.setDescription(promotionType.getDescription());
        return promotionTypeDto;
    }

    @Override
    public PromotionType maptoPromotionType(PromotionTypeDto promotionTypeDto, PromotionType existingPromotionType) {
        if (promotionTypeDto == null) {
            return null;
        }
        PromotionType promotionType = (existingPromotionType != null) ? existingPromotionType : new PromotionType();
        if (existingPromotionType == null) {
            promotionType.setId(promotionTypeDto.getId());
        }
        promotionType.setName(promotionTypeDto.getName());
        promotionType.setDescription(promotionTypeDto.getDescription());
        return promotionType;
    }
}
