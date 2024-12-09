package com.horizon.repository;

import com.horizon.domain.PromotionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionTypeRepository extends JpaRepository<PromotionType, Integer> {
    @Query("SELECT pt FROM Promotion p " +
            "JOIN PromotionCondition pc ON p.id = pc.promotion.id " +
            "JOIN PromotionType pt ON pc.promotionType.id = pt.id " +
            "WHERE p.id = :promotionId")
    List<PromotionType> findByPromotionId(@Param("promotionId") Integer promotionId);

}
