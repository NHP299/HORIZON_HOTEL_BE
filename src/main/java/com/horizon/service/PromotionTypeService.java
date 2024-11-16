package com.horizon.service;

import com.horizon.dto.PromotionTypeDto;

import java.util.List;

public interface PromotionTypeService {
    PromotionTypeDto createPromotionType(PromotionTypeDto promotionTypeDto);
    PromotionTypeDto updatePromotionType(Integer promotionTypeId, PromotionTypeDto promotionTypeDto);
    void deletePromotionType(Integer promotionTypeId);
    PromotionTypeDto getPromotionTypeById(Integer promotionTypeId);
    List<PromotionTypeDto> getAllPromotionTypes();


}
