package com.horizon.service;

import com.horizon.dto.PromotionTypeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PromotionTypeService {
    PromotionTypeDto createPromotionType(PromotionTypeDto promotionTypeDto);
    PromotionTypeDto updatePromotionType(Integer promotionTypeId, PromotionTypeDto promotionTypeDto);
    void deletePromotionType(Integer promotionTypeId);
    PromotionTypeDto getPromotionTypeById(Integer promotionTypeId);
    Page<PromotionTypeDto> getAllPromotionTypes(Pageable pageable);


}
