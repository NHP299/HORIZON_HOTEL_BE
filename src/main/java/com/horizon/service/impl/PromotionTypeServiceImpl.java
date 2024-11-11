package com.horizon.service.impl;

import com.horizon.domain.PromotionType;
import com.horizon.dto.PromotionTypeDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.PromotionTypeMapper;
import com.horizon.repository.PromotionTypeRepository;
import com.horizon.service.PromotionTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PromotionTypeServiceImpl implements PromotionTypeService {
    private PromotionTypeRepository promotionTypeRepository;
    private PromotionTypeMapper promotionTypeMapper;

    @Override
    public PromotionTypeDto createPromotionType(PromotionTypeDto promotionTypeDto) {
        PromotionType promotionType = promotionTypeMapper.maptoPromotionType(promotionTypeDto, null);
        PromotionType savedPromotionType = promotionTypeRepository.save(promotionType);
        return promotionTypeMapper.maptoPromotionTypeDto(savedPromotionType);
    }

    @Override
    public PromotionTypeDto updatePromotionType(Integer promotionTypeId, PromotionTypeDto promotionTypeDto) {
        PromotionType existingPromotionType = promotionTypeRepository.findById(promotionTypeId).orElseThrow(()->new ResourceNotFoundException("Promotion type not found " + promotionTypeId) );
        PromotionType updatedPromotionType = promotionTypeMapper.maptoPromotionType(promotionTypeDto, existingPromotionType);
        updatedPromotionType = promotionTypeRepository.save(updatedPromotionType);
        return promotionTypeMapper.maptoPromotionTypeDto(updatedPromotionType);
    }

    @Override
    public void deletePromotionType(Integer promotionTypeId) {
        PromotionType promotionType = promotionTypeRepository.findById(promotionTypeId).orElseThrow(()->new ResourceNotFoundException("Promotion type not found " + promotionTypeId) );
        promotionTypeRepository.delete(promotionType);
    }

    @Override
    public PromotionTypeDto getPromotionTypeById(Integer promotionTypeId) {
        PromotionType promotionType = promotionTypeRepository.findById(promotionTypeId).orElseThrow(()->new ResourceNotFoundException("Promotion type not found " + promotionTypeId) );
        return promotionTypeMapper.maptoPromotionTypeDto(promotionType);
    }

    @Override
    public Page<PromotionTypeDto> getAllPromotionTypes(Pageable pageable) {
        Page<PromotionType> promotionTypes = promotionTypeRepository.findAll(pageable);
        return promotionTypes.map(promotionTypeMapper::maptoPromotionTypeDto);
    }
}
