package com.horizon.service.impl;

import com.horizon.domain.PromotionType;
import com.horizon.dto.PromotionTypeDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.PromotionTypeMapper;
import com.horizon.repository.PromotionTypeRepository;
import com.horizon.service.PromotionTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PromotionTypeServiceImpl implements PromotionTypeService {
    private PromotionTypeRepository promotionTypeRepository;
    private PromotionTypeMapper promotionTypeMapper;

    @Override
    public PromotionTypeDto create(PromotionTypeDto promotionTypeDto) {
        PromotionType promotionType = promotionTypeMapper.maptoPromotionType(promotionTypeDto, null);
        PromotionType savedPromotionType = promotionTypeRepository.save(promotionType);
        return promotionTypeMapper.maptoPromotionTypeDto(savedPromotionType);
    }

    @Override
    public PromotionTypeDto update(Integer promotionTypeId, PromotionTypeDto promotionTypeDto) {
        PromotionType existingPromotionType = promotionTypeRepository.findById(promotionTypeId).orElseThrow(()->new ResourceNotFoundException("Promotion type not found " + promotionTypeId) );
        PromotionType updatedPromotionType = promotionTypeMapper.maptoPromotionType(promotionTypeDto, existingPromotionType);
        updatedPromotionType = promotionTypeRepository.save(updatedPromotionType);
        return promotionTypeMapper.maptoPromotionTypeDto(updatedPromotionType);
    }

    @Override
    public void delete(Integer promotionTypeId) {
        PromotionType promotionType = promotionTypeRepository.findById(promotionTypeId).orElseThrow(()->new ResourceNotFoundException("Promotion type not found " + promotionTypeId) );
        promotionTypeRepository.delete(promotionType);
    }

    @Override
    public PromotionTypeDto getById(Integer promotionTypeId) {
        PromotionType promotionType = promotionTypeRepository.findById(promotionTypeId).orElseThrow(()->new ResourceNotFoundException("Promotion type not found " + promotionTypeId) );
        return promotionTypeMapper.maptoPromotionTypeDto(promotionType);
    }

    @Override
    public List<PromotionTypeDto> getAll() {
        List<PromotionType> promotionTypes = promotionTypeRepository.findAll();
        return promotionTypes.stream().map(promotionTypeMapper::maptoPromotionTypeDto).toList();
    }

    @Override
    public List<PromotionTypeDto> findByPromotionId(Integer promotionId) {
        List<PromotionType> promotionTypes = promotionTypeRepository.findByPromotionId(promotionId);
        return promotionTypes.stream().map(promotionTypeMapper::maptoPromotionTypeDto).toList();
    }
}
