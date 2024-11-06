package com.horizon.service.impl;

import com.horizon.domain.Promotion;
import com.horizon.dto.PromotionDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.PromotionMapper;
import com.horizon.repository.PromotionRepository;
import com.horizon.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PromotionServiceImpl implements PromotionService
{
    private PromotionRepository promotionRepository;
    private PromotionMapper promotionMapper;

    @Override
    public PromotionDto createPromotion(PromotionDto promotionDto) {
        Promotion promotion = promotionMapper.maptoPromotion(promotionDto,null);
        Promotion savedPromotion = promotionRepository.save(promotion);
        return promotionMapper.maptoPromotionDto(savedPromotion);
    }

    @Override
    public PromotionDto updatePromotion(Integer promotionId, PromotionDto promotionDto) {
        Promotion existingPromotion = promotionRepository.findById(promotionId).orElseThrow(() -> new ResourceNotFoundException("Promotion not found" + promotionId));
        Promotion updatedPromotion = promotionMapper.maptoPromotion(promotionDto,existingPromotion);
        updatedPromotion = promotionRepository.save(updatedPromotion);
        return promotionMapper.maptoPromotionDto(updatedPromotion);
    }

    @Override
    public void deletePromotion(Integer promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId).orElseThrow(() -> new ResourceNotFoundException("Promotion not found" + promotionId));
        promotionRepository.delete(promotion);
    }

    @Override
    public PromotionDto getPromotionById(Integer promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId).orElseThrow(() -> new ResourceNotFoundException("Promotion not found " + promotionId));
        return promotionMapper.maptoPromotionDto(promotion);
    }

    @Override
    public Page<PromotionDto> getAllPromotions(Pageable pageable) {
        Page<Promotion> promotions = promotionRepository.findAll(pageable);
        return promotions.map(promotionMapper::maptoPromotionDto);
    }

    @Override
    public Page<PromotionDto> getPromotionByName(String name, Pageable pageable) {
        Page<Promotion> promotions = promotionRepository.findByNameContainingIgnoreCase(name, pageable);
        return promotions.map(promotionMapper::maptoPromotionDto);
    }

    @Override
    public PromotionDto getPromotionByIdAndAvailable(Integer promotionId) {
        Promotion promotion = promotionRepository.findPromotionByIdAndAvailable(promotionId).orElseThrow(() -> new ResourceNotFoundException("Promotion not found " + promotionId));
        return promotionMapper.maptoPromotionDto(promotion);
    }

    @Override
    public Page<PromotionDto> getAllAvailablePromotions(Pageable pageable) {
        Page<Promotion> promotions = promotionRepository.findAllAvailablePromotions(pageable);
        return promotions.map(promotionMapper::maptoPromotionDto);
    }

    @Override
    public Page<PromotionDto> getPromotionByNameAndAvailable(String name, Pageable pageable) {
        Page<Promotion> promotions = promotionRepository.findPromotionByNameContainingAndAvailable(name, pageable);
        return promotions.map(promotionMapper::maptoPromotionDto);
    }
}
