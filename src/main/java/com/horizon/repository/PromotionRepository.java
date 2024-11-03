package com.horizon.repository;

import com.horizon.domain.Promotion;
import com.horizon.dto.PromotionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    Page<Promotion> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("SELECT p FROM Promotion p WHERE p.id = :promotionId AND CURRENT_TIMESTAMP BETWEEN p.startTime AND p.endTime")
    Optional<Promotion> findPromotionByIdAndTimeRange(@Param("promotionId") Integer promotionId);

    @Query("SELECT p FROM Promotion p WHERE CURRENT_TIMESTAMP BETWEEN p.startTime AND p.endTime")
    Page<Promotion> findAllPromotionsWithinTimeRange(Pageable pageable);


}
