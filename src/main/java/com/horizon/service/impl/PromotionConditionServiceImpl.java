package com.horizon.service.impl;

import com.horizon.domain.PromotionCondition;
import com.horizon.dto.PromotionConditionDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.PromotionConditionMapper;
import com.horizon.repository.PromotionConditionRepository;
import com.horizon.service.PromotionConditionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PromotionConditionServiceImpl implements PromotionConditionService {
    private PromotionConditionRepository promotionConditionRepository;
    private PromotionConditionMapper promotionConditionMapper;

    @Override
    public PromotionConditionDto createPromotionCondition(PromotionConditionDto promotionConditionDto) {
        PromotionCondition promotionCondition = promotionConditionMapper.maptoPromotionCondition(promotionConditionDto,null);
        PromotionCondition savedpromotionCondition = promotionConditionRepository.save(promotionCondition);
        return promotionConditionMapper.maptoPromotionConditionDto(savedpromotionCondition);
    }

    @Override
    public PromotionConditionDto updatePromotionCondition(Integer promotionConditionId, PromotionConditionDto promotionConditionDto) {
        PromotionCondition existingPromotionCondition = promotionConditionRepository.findById(promotionConditionId).orElseThrow(() -> new ResourceNotFoundException("Promotion condition not found " + promotionConditionId));
        PromotionCondition updatedPromotionCondition = promotionConditionMapper.maptoPromotionCondition(promotionConditionDto,existingPromotionCondition);
        updatedPromotionCondition = promotionConditionRepository.save(updatedPromotionCondition);
        return promotionConditionMapper.maptoPromotionConditionDto(updatedPromotionCondition);
    }

    @Override
    public void deletePromotionCondition(Integer promotionConditionId) {
        PromotionCondition promotionCondition = promotionConditionRepository.findById(promotionConditionId).orElseThrow(() -> new ResourceNotFoundException("Promotion condition not found " + promotionConditionId));
        promotionConditionRepository.delete(promotionCondition);
    }

    @Override
    public PromotionConditionDto getPromotionConditionById(Integer promotionConditionId) {
        PromotionCondition promotionCondition = promotionConditionRepository.findById(promotionConditionId).orElseThrow(() -> new ResourceNotFoundException("Promotion condition not found " + promotionConditionId));
        return promotionConditionMapper.maptoPromotionConditionDto(promotionCondition);
    }

    @Override
    public List<PromotionConditionDto> getAllPromotionConditions() {
        List<PromotionCondition> promotionConditions = promotionConditionRepository.findAll();
        return promotionConditions.stream().map(promotionConditionMapper::maptoPromotionConditionDto).toList();
    }
}
