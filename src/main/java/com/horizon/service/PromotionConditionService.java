package com.horizon.service;

import com.horizon.dto.PromotionConditionDto;

import java.util.List;

public interface PromotionConditionService {
    PromotionConditionDto create(PromotionConditionDto promotionConditionDto);
    PromotionConditionDto update(Integer promotionConditionId, PromotionConditionDto promotionConditionDto);
    void delete(Integer promotionConditionId);
    PromotionConditionDto getById(Integer promotionConditionId);
    List<PromotionConditionDto> getAll();
}
