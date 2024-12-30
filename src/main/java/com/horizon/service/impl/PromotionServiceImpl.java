package com.horizon.service.impl;

import com.horizon.domain.Promotion;
import com.horizon.dto.PromotionDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.PromotionMapper;
import com.horizon.repository.PromotionRepository;
import com.horizon.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PromotionServiceImpl implements PromotionService {
    private PromotionRepository promotionRepository;
    private PromotionMapper promotionMapper;

    @Override
    public PromotionDto create(PromotionDto promotionDto) {
        Promotion promotion = promotionMapper.toPromotion(promotionDto);
        promotion.setIsActivated(true);
        Promotion savedPromotion = promotionRepository.save(promotion);
        return promotionMapper.toPromotionDto(savedPromotion);
    }

    @Override
    public PromotionDto update(Integer promotionId, PromotionDto promotionDto) {
        Promotion updatedPromotion = promotionRepository.findById(promotionId)
                .map(existingPromotion -> {
                    existingPromotion = promotionMapper.toPromotion(promotionDto);
                    existingPromotion.setId(promotionId);
                    existingPromotion.setIsActivated(true);
                    return promotionRepository.save(existingPromotion);
                }).orElseThrow(() -> new ResourceNotFoundException("Promotion not found" + promotionId));
        return promotionMapper.toPromotionDto(updatedPromotion);
    }

    @Override
    public void delete(Integer promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId).orElseThrow(() -> new ResourceNotFoundException("Promotion not found" + promotionId));
        promotion.setIsActivated(false);
        promotionRepository.save(promotion);
    }

    @Override
    public PromotionDto getById(Integer promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId).orElseThrow(() -> new ResourceNotFoundException("Promotion not found " + promotionId));
        return promotionMapper.toPromotionDto(promotion);
    }

    @Override
    public List<PromotionDto> getAll() {
        List<Promotion> promotions = promotionRepository.findAllByIsActivatedTrue();
        return promotions.stream().map(promotionMapper::toPromotionDto).toList();
    }

    @Override
    public List<PromotionDto> getByName(String name) {
        List<Promotion> promotions = promotionRepository.findByNameContainingIgnoreCaseAndIsActivatedTrue(name);
        return promotions.stream().map(promotionMapper::toPromotionDto).toList();
    }

    @Override
    public PromotionDto getAvailableById(Integer promotionId) {
        Promotion promotion = promotionRepository.findAvailableById(promotionId).orElseThrow(() -> new ResourceNotFoundException("Promotion not found" + promotionId));
        return promotionMapper.toPromotionDto(promotion);
    }

    @Override
    public List<PromotionDto> getAllAvailable(Integer roomTypeId) {
        List<Promotion> promotions = promotionRepository.findAllAvailable(roomTypeId);
        return promotions.stream().map(promotionMapper::toPromotionDto).toList();
    }

    @Override
    public Double apply(Integer promotionId, Double totalPrice) {
        double discount = 0;
        if (promotionId != null) {
            Promotion promotion = promotionRepository.findById(promotionId)
                    .orElseThrow(() -> new IllegalArgumentException("Promotion with ID " + promotionId + " not found."));
            if ("PERCENTAGE".equalsIgnoreCase(promotion.getDiscountType().name())) {
                discount = totalPrice * promotion.getDiscountValue().doubleValue();
            } else if ("FIXED".equalsIgnoreCase(promotion.getDiscountType().name())) {
                discount = promotion.getDiscountValue().doubleValue();
            }
            totalPrice -= discount;
        }
        return totalPrice;
    }


}
