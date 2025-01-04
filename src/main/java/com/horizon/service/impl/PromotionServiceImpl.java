package com.horizon.service.impl;

import com.horizon.domain.Promotion;
import com.horizon.dto.PromotionDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.PromotionMapper;
import com.horizon.repository.PromotionRepository;
import com.horizon.repository.RoomTypeRepository;
import com.horizon.service.PromotionService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PromotionServiceImpl implements PromotionService {
    private PromotionRepository promotionRepository;
    private PromotionMapper promotionMapper;
    private RoomTypeRepository roomTypeRepository;

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
    public Page<PromotionDto> getAll(Pageable pageable) {
        Page<Promotion> promotions = promotionRepository.findAllByIsActivatedTrue(pageable);
        return promotions.map(promotionMapper::toPromotionDto);
    }

    @Override
    public Page<PromotionDto> getByName(String name, Pageable pageable) {
        Page<Promotion> promotions = promotionRepository.findByNameContainingIgnoreCaseAndIsActivatedTrue(name, pageable);
        return promotions.map(promotionMapper::toPromotionDto);
    }

    @Override
    public PromotionDto getAvailableById(Integer promotionId) {
        Promotion promotion = promotionRepository.findAvailableById(promotionId).orElseThrow(() -> new ResourceNotFoundException("Promotion not found" + promotionId));
        return promotionMapper.toPromotionDto(promotion);
    }

    @Override
    public Page<PromotionDto> getAllAvailable(Integer roomTypeId, Pageable pageable) {
        Page<Promotion> promotions = promotionRepository.findAllAvailable(roomTypeId, pageable);
        return promotions.map(promotionMapper::toPromotionDto);
    }

    @Override
    public Double apply(Integer promotionId, Double totalPrice) {
        double discount = 0;
        if (promotionId != null) {
            Promotion promotion = promotionRepository.findById(promotionId)
                    .orElseThrow(() -> new IllegalArgumentException("Promotion with ID " + promotionId + " not found."));
            if ("PERCENTAGE".equalsIgnoreCase(promotion.getDiscountType().name())) {
                discount = totalPrice * promotion.getDiscountValue() / 100;
            } else if ("FIXED".equalsIgnoreCase(promotion.getDiscountType().name())) {
                discount = promotion.getDiscountValue();
            }
            totalPrice -= discount;
        }
        return totalPrice;
    }

    @Transactional
    @Scheduled(fixedRate = 60000)
    public void updateIsActivated() {
        List<Integer> inactiveRoomTypeIds = roomTypeRepository.findInactiveRoomTypeIds();
        if (!inactiveRoomTypeIds.isEmpty()) {
            promotionRepository.deactivatePromotionsByRoomTypeIds(inactiveRoomTypeIds);
        }
    }

}
