package com.horizon.repository;

import com.horizon.domain.Promotion;
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
    Optional<Promotion> findPromotionByIdAndAvailable(@Param("promotionId") Integer promotionId);

    @Query("SELECT p FROM Promotion p WHERE CURRENT_TIMESTAMP BETWEEN p.startTime AND p.endTime")
    Page<Promotion> findAllAvailablePromotions(Pageable pageable);

    @Query("SELECT p FROM Promotion p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :promotionName, '%')) AND CURRENT_TIMESTAMP BETWEEN p.startTime AND p.endTime")
    Page<Promotion> findPromotionByNameContainingAndAvailable(@Param("promotionName") String promotionName, Pageable pageable);


}
