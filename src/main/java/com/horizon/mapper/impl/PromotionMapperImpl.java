package com.horizon.mapper.impl;

import com.horizon.domain.Promotion;
import com.horizon.domain.RoomType;
import com.horizon.dto.PromotionDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.PromotionMapper;
import com.horizon.repository.RoomTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PromotionMapperImpl implements PromotionMapper {
    private RoomTypeRepository roomTypeRepository;

    @Override
    public PromotionDto toPromotionDto(Promotion promotion) {
        if (promotion == null) {
            return null;
        }

        PromotionDto promotionDto = new PromotionDto();
        promotionDto.setId(promotion.getId());
        promotionDto.setName(promotion.getName());
        promotionDto.setDescription(promotion.getDescription());
        promotionDto.setDiscountType(promotion.getDiscountType());
        promotionDto.setDiscountValue(promotion.getDiscountValue());
        promotionDto.setStartDate(promotion.getStartDate());
        promotionDto.setEndDate(promotion.getEndDate());
        promotionDto.setRoomTypeId(promotion.getRoomType().getId());
        promotionDto.setIsActivated(promotion.getIsActivated());
        promotionDto.setCreatedAt(promotion.getCreatedAt());
        promotionDto.setUpdatedAt(promotion.getUpdatedAt());
        return promotionDto;
    }

    @Override
    public Promotion toPromotion(PromotionDto promotionDto) {
        if (promotionDto == null) {
            return null;
        }

        Promotion promotion =  new Promotion();
        promotion.setId(promotionDto.getId());
        promotion.setName(promotionDto.getName());
        promotion.setDescription(promotionDto.getDescription());
        promotion.setDiscountType(promotionDto.getDiscountType());
        promotion.setDiscountValue(promotionDto.getDiscountValue());
        promotion.setStartDate(promotionDto.getStartDate());
        promotion.setEndDate(promotionDto.getEndDate());
        promotion.setRoomType(findRoomTypeById(promotionDto.getRoomTypeId()));
        promotion.setIsActivated(promotionDto.getIsActivated());
        return promotion;
    }

    private RoomType findRoomTypeById(Integer id) {
        return roomTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("RoomType not found with id: " + id));
    }
}
