package com.horizon.repository;

import com.horizon.domain.PromotionCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface PromotionConditionRepository extends JpaRepository<PromotionCondition, Integer> {
}
