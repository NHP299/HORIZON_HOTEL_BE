package com.horizon.service.impl;

import com.horizon.domain.Promotion;
import com.horizon.dto.PromotionDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.PromotionMapper;
import com.horizon.repository.PromotionRepository;
import com.horizon.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
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
        Promotion savedPromotion = promotionRepository.save(promotion);
        return promotionMapper.toPromotionDto(savedPromotion);
    }

    @Override
    public PromotionDto update(Integer promotionId, PromotionDto promotionDto) {
        Promotion updatedPromotion = promotionRepository.findById(promotionId)
                .map(existingPromotion -> {
                    existingPromotion = promotionMapper.toPromotion(promotionDto);
                    existingPromotion.setId(promotionId);
                    return promotionRepository.save(existingPromotion);
                }).orElseThrow(() -> new ResourceNotFoundException("Promotion not found" + promotionId));
        return promotionMapper.toPromotionDto(updatedPromotion);
    }

    @Override
    public void delete(Integer promotionId) {
        Promotion promotion = promotionRepository.findById(promotionId).orElseThrow(() -> new ResourceNotFoundException("Promotion not found" + promotionId));
        promotion.setStatus(Promotion.Status.INACTIVE);
        promotionRepository.save(promotion);
    }

    @Override
    public PromotionDto getById(Integer promotionId) {
        Promotion promotion = promotionRepository.findByIdAndStatus(promotionId, Promotion.Status.ACTIVE).orElseThrow(() -> new ResourceNotFoundException("Promotion not found " + promotionId));
        return promotionMapper.toPromotionDto(promotion);
    }

    @Override
    public List<PromotionDto> getAll() {
        List<Promotion> promotions = promotionRepository.findAllByStatus(Promotion.Status.ACTIVE);
        return promotions.stream().map(promotionMapper::toPromotionDto).toList();
    }

    @Override
    public List<PromotionDto> getByName(String name) {
        List<Promotion> promotions = promotionRepository.findByNameContainingIgnoreCaseAndStatus(name, Promotion.Status.ACTIVE);
        return promotions.stream().map(promotionMapper::toPromotionDto).toList();
    }

    @Override
    public PromotionDto getAvailableById(Integer promotionId) {
        Promotion promotion = promotionRepository.findAvailableById(promotionId);
        return promotionMapper.toPromotionDto(promotion);
    }

    @Override
    public List<PromotionDto> getAllAvailable(Integer roomTypeId) {
        List<Promotion> promotions = promotionRepository.findAllAvailable(roomTypeId);
        return promotions.stream().map(promotionMapper::toPromotionDto).toList();
    }

    @Scheduled(cron = "0 * * * * ?")
    public void updatePromotionStatus() {
        promotionRepository.updateStatusIfExpired();
        System.out.println("Promotion statuses updated.");
    }

}
