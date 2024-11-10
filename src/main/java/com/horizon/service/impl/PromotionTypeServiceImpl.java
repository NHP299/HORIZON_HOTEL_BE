package com.horizon.service.impl;

import com.horizon.repository.PromotionTypeRepository;
import com.horizon.service.PromotionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionTypeServiceImpl implements PromotionTypeService {
    private final PromotionTypeRepository promotionTypeRepository;

    @Autowired
    private PromotionTypeServiceImpl(PromotionTypeRepository promotionTypeRepository) {
        this.promotionTypeRepository = promotionTypeRepository;
    }
}
