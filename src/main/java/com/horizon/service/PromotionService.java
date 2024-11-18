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
    PromotionDto getByIdAndAvailable(Integer promotionId);
    List<PromotionDto> getAllAvailable();
    List<PromotionDto> getByNameAndAvailable(String name);
    List<PromotionDto> getApplicable(Integer daysOfBooking, Integer roomsOfBooking);
    void applyPromotionUsage(Integer promotionId);
}
