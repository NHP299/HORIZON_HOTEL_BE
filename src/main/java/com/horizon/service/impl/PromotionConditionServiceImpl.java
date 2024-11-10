package com.horizon.service.impl;

import com.horizon.repository.PromotionConditionRepository;
import com.horizon.service.PromotionConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionConditionServiceImpl implements PromotionConditionService {
    private final PromotionConditionRepository promotionConditionRepository;

    @Autowired
    private PromotionConditionServiceImpl(PromotionConditionRepository promotionConditionRepository) {
        this.promotionConditionRepository = promotionConditionRepository;
    }
}
