package com.horizon.service.impl;

import com.horizon.repository.PromotionRepository;
import com.horizon.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PromotionServiceImpl implements PromotionService
{
    private final PromotionRepository promotionRepository;

    @Autowired
    private PromotionServiceImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }


}
