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
    public List<PromotionDto> getAllPromotions() {
        List<Promotion> promotions = promotionRepository.findAll();
        return promotions.stream().map(promotionMapper::maptoPromotionDto).toList();
    }

    @Override
    public List<PromotionDto> getPromotionByName(String name) {
        List<Promotion> promotions = promotionRepository.findByNameContainingIgnoreCase(name);
        return promotions.stream().map(promotionMapper::maptoPromotionDto).toList();
    }

    @Override
    public PromotionDto getPromotionByIdAndAvailable(Integer promotionId) {
        Promotion promotion = promotionRepository.findPromotionByIdAndAvailable(promotionId).orElseThrow(() -> new ResourceNotFoundException("Promotion not found " + promotionId));
        return promotionMapper.maptoPromotionDto(promotion);
    }

    @Override
    public List<PromotionDto> getAllAvailablePromotions() {
        List<Promotion> promotions = promotionRepository.findAllAvailablePromotions();
        return promotions.stream().map(promotionMapper::maptoPromotionDto).toList();
    }

    @Override
    public List<PromotionDto> getPromotionByNameAndAvailable(String name) {
        List<Promotion> promotions = promotionRepository.findPromotionByNameContainingAndAvailable(name);
        return promotions.stream().map(promotionMapper::maptoPromotionDto).toList();
    }

    @Override
    public List<PromotionDto> getApplicablePromotions(Integer daysOfBooking, Integer roomsOfBooking) {
        List<Promotion> promotions = promotionRepository.findApplicablePromotions(daysOfBooking, roomsOfBooking);
        return promotions.stream().map(promotionMapper::maptoPromotionDto).toList();
    }
}
