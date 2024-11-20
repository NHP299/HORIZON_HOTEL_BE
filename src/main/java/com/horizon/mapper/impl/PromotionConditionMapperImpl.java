package com.horizon.mapper.impl;

import com.horizon.domain.Promotion;
import com.horizon.domain.PromotionCondition;
import com.horizon.domain.PromotionType;
import com.horizon.dto.PromotionConditionDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.PromotionConditionMapper;
import com.horizon.repository.PromotionRepository;
import com.horizon.repository.PromotionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionConditionMapperImpl implements PromotionConditionMapper {
    private final PromotionTypeRepository promotionTypeRepository;
    private final PromotionRepository promotionRepository;

    @Autowired
    public PromotionConditionMapperImpl(PromotionTypeRepository promotionTypeRepository, PromotionRepository promotionRepository) {
        this.promotionTypeRepository = promotionTypeRepository;
        this.promotionRepository = promotionRepository;
    }

    @Override
    public PromotionConditionDto maptoPromotionConditionDto(PromotionCondition promotionCondition) {
        if (promotionCondition == null) {
            return null;
        }
        PromotionConditionDto promotionConditionDto = new PromotionConditionDto();
        promotionConditionDto.setId(promotionCondition.getId());
        promotionConditionDto.setPromotionId(promotionCondition.getPromotion().getId());
        promotionConditionDto.setPromotionTypeId(promotionCondition.getPromotionType().getId());
        promotionConditionDto.setValue(promotionCondition.getValue());
        promotionConditionDto.setStartTime(promotionCondition.getStartTime());
        promotionConditionDto.setEndTime(promotionCondition.getEndTime());
        return promotionConditionDto;
    }

    @Override
    public PromotionCondition maptoPromotionCondition(PromotionConditionDto promotionConditionDto, PromotionCondition existingPromotionCondition) {
        if (promotionConditionDto == null) {
            return null;
        }
        PromotionCondition promotionCondition = (existingPromotionCondition != null) ? existingPromotionCondition : new PromotionCondition();
        if (existingPromotionCondition == null) {
            promotionCondition.setId(promotionConditionDto.getId());
        }
        promotionCondition.setPromotion(findPromotionById(promotionConditionDto.getPromotionId()));
        promotionCondition.setPromotionType(findPromotionTypeById(promotionConditionDto.getPromotionTypeId()));
        promotionCondition.setValue(promotionConditionDto.getValue());
        promotionCondition.setStartTime(promotionConditionDto.getStartTime());
        promotionCondition.setEndTime(promotionConditionDto.getEndTime());
        return promotionCondition;
    }

    private Promotion findPromotionById(Integer id) {
        return promotionRepository .findById(id).orElseThrow(() -> new ResourceNotFoundException("Promotion not found with id: " + id));
    }

    private PromotionType findPromotionTypeById(Integer id) {
        return promotionTypeRepository .findById(id).orElseThrow(() -> new ResourceNotFoundException("PromotionType not found with id: " + id));
    }
}
