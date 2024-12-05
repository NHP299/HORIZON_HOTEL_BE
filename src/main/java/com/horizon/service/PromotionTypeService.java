package com.horizon.service;

import com.horizon.dto.PromotionTypeDto;

import java.util.List;

public interface PromotionTypeService {
    PromotionTypeDto create(PromotionTypeDto promotionTypeDto);
    PromotionTypeDto update(Integer promotionTypeId, PromotionTypeDto promotionTypeDto);
    void delete(Integer promotionTypeId);
    PromotionTypeDto getById(Integer promotionTypeId);
    List<PromotionTypeDto> getAll();
    List<PromotionTypeDto> findByPromotionId(Integer promotionId);


}
