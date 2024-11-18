package com.horizon.service.impl;

import com.horizon.domain.Promotion;
import com.horizon.dto.PromotionDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.PromotionMapper;
import com.horizon.repository.PromotionRepository;
import com.horizon.service.PromotionService;
import jakarta.transaction.Transactional;
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
    public PromotionDto create(PromotionDto promotionDto) {
        Promotion promotion = promotionMapper.maptoPromotion(promotionDto,null);
        Promotion savedPromotion = promotionRepository.save(promotion);
        return promotionMapper.maptoPromotionDto(savedPromotion);
    }

    @Override
    public PromotionDto update(Integer promotionId, PromotionDto promotionDto) {
        Promotion existingPromotion = promotionRepository.findById(promotionId).orElseThrow(() -> new ResourceNotFoundException("Promotion not found" + promotionId));
        Promotion updatedPromotion = promotionMapper.maptoPromotion(promotionDto,existingPromotion);
        updatedPromotion = promotionRepository.save(updatedPromotion);
        return promotionMapper.maptoPromotionDto(updatedPromotion);
    }

    @Override
    public void delete(Integer promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId).orElseThrow(() -> new ResourceNotFoundException("Promotion not found" + promotionId));
        promotionRepository.delete(promotion);
    }

    @Override
    public PromotionDto getById(Integer promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId).orElseThrow(() -> new ResourceNotFoundException("Promotion not found " + promotionId));
        return promotionMapper.maptoPromotionDto(promotion);
    }

    @Override
    public List<PromotionDto> getAll() {
        List<Promotion> promotions = promotionRepository.findAll();
        return promotions.stream().map(promotionMapper::maptoPromotionDto).toList();
    }

    @Override
    public List<PromotionDto> getByName(String name) {
        List<Promotion> promotions = promotionRepository.findByNameContainingIgnoreCase(name);
        return promotions.stream().map(promotionMapper::maptoPromotionDto).toList();
    }

    @Override
    public PromotionDto getByIdAndAvailable(Integer promotionId) {
        Promotion promotion = promotionRepository.findByIdAndAvailable(promotionId).orElseThrow(() -> new ResourceNotFoundException("Promotion not found " + promotionId));
        return promotionMapper.maptoPromotionDto(promotion);
    }

    @Override
    public List<PromotionDto> getAllAvailable() {
        List<Promotion> promotions = promotionRepository.findAllAvailable();
        return promotions.stream().map(promotionMapper::maptoPromotionDto).toList();
    }

    @Override
    public List<PromotionDto> getByNameAndAvailable(String name) {
        List<Promotion> promotions = promotionRepository.findByNameContainingAndAvailable(name);
        return promotions.stream().map(promotionMapper::maptoPromotionDto).toList();
    }

    @Override
    public List<PromotionDto> getApplicable(Integer daysOfBooking, Integer roomsOfBooking) {
        List<Promotion> promotions = promotionRepository.findApplicable(daysOfBooking, roomsOfBooking);
        return promotions.stream().map(promotionMapper::maptoPromotionDto).toList();
    }

    @Override
    @Transactional
    public void applyPromotionUsage(Integer promotionId) {
        int rowsUpdated = promotionRepository.decrementMaxUsage(promotionId);
        if (rowsUpdated == 0) {
            throw new IllegalArgumentException("Promotion usage limit reached or invalid promotion ID.");
        }
    }
}
