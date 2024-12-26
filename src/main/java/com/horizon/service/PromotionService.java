package com.horizon.service;

import com.horizon.dto.PromotionDto;
import java.util.List;

public interface PromotionService {
    PromotionDto create(PromotionDto promotionDto);
    PromotionDto update(Integer promotionId,PromotionDto promotionDto);
    void delete(Integer promotionId);
    PromotionDto getById(Integer promotionId);
    List<PromotionDto> getAll();
    List<PromotionDto> getByName(String name);

    PromotionDto getAvailableById(Integer promotionId);
    List<PromotionDto> getAllAvailable(Integer roomTypeId);

    Double apply(Integer promotionId, Double totalPrice);
}
