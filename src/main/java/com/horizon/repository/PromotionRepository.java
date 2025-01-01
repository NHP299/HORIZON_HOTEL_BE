package com.horizon.repository;

import com.horizon.domain.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    Optional<Promotion> findByIdAndIsActivatedTrue(Integer id);
    List<Promotion> findByNameContainingIgnoreCaseAndIsActivatedTrue(String name);
    List<Promotion> findAllByIsActivatedTrue();

    @Query("SELECT p FROM Promotion p WHERE p.isActivated = true AND p.startDate <= CURRENT_DATE AND p.endDate >= CURRENT_DATE AND p.id = :promotionId")
    Optional<Promotion> findAvailableById(@Param("promotionId") Integer promotionId);

    @Query("SELECT p FROM Promotion p WHERE p.isActivated = true AND p.startDate <= CURRENT_DATE AND p.endDate >= CURRENT_DATE AND (p.roomType.id = :roomTypeId OR p.roomType IS NULL)")
    List<Promotion> findAllAvailable(@Param("roomTypeId") Integer roomTypeId);




}
