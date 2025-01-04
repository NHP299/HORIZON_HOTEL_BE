package com.horizon.service;

import com.horizon.dto.PromotionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PromotionService {
    PromotionDto create(PromotionDto promotionDto);
    PromotionDto update(Integer promotionId,PromotionDto promotionDto);
    void delete(Integer promotionId);
    PromotionDto getById(Integer promotionId);
    Page<PromotionDto> getAll(Pageable pageable);
    Page<PromotionDto> getByName(String name, Pageable pageable);

    PromotionDto getAvailableById(Integer promotionId);
    Page<PromotionDto> getAllAvailable(Integer roomTypeId, Pageable pageable);

    Double apply(Integer promotionId, Double totalPrice);
}
