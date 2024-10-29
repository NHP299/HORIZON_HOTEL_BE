package com.horizon.mapper.impl;

import com.horizon.domain.Promotion;
import com.horizon.dto.PromotionDto;
import com.horizon.mapper.PromotionMapper;
import org.springframework.stereotype.Service;


@Service
public class PromotionMapperImpl implements PromotionMapper {

    @Override
    public PromotionDto maptoPromotionDto(Promotion promotion) {
        if (promotion == null) {
            return null;
        }
        PromotionDto promotionDto = new PromotionDto();
        promotionDto.setId(promotion.getId());
        promotionDto.setName(promotion.getName());
        promotionDto.setDescription(promotion.getDescription());
        promotionDto.setDiscountCondition(promotion.getDiscountCondition());
        promotionDto.setDiscount(promotion.getDiscount());
        promotionDto.setStartTime(promotion.getStartTime());
        promotionDto.setEndTime(promotion.getEndTime());
        promotionDto.setMaxUsage(promotion.getMaxUsage());
        promotionDto.setVoucherType(promotion.getVoucherType());
        promotionDto.setMaxAmount(promotion.getMaxAmount());
        return promotionDto;
    }

    @Override
    public Promotion maptoPromotion(PromotionDto promotionDto, Promotion existingPromotion) {
        if (promotionDto == null) {
            return null;
        }

        Promotion promotion = (existingPromotion != null) ? existingPromotion : new Promotion();
        if (existingPromotion == null) {
            promotion.setId(promotionDto.getId());
        }

        promotion.setName(promotionDto.getName());
        promotion.setDescription(promotionDto.getDescription());
        promotion.setDiscountCondition(promotionDto.getDiscountCondition());
        promotion.setDiscount(promotionDto.getDiscount());
        promotion.setStartTime(promotionDto.getStartTime());
        promotion.setEndTime(promotionDto.getEndTime());
        promotion.setMaxUsage(promotionDto.getMaxUsage());
        promotion.setVoucherType(promotionDto.getVoucherType());
        promotion.setMaxAmount(promotionDto.getMaxAmount());

        return promotion;
    }
}
