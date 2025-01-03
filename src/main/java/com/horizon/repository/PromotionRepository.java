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
    Optional<Promotion> findByIdAndIsActivatedTrue(Integer id);
    Page<Promotion> findByNameContainingIgnoreCaseAndIsActivatedTrue(String name, Pageable pageable);
    Page<Promotion> findAllByIsActivatedTrue(Pageable pageable);

    @Query("SELECT p FROM Promotion p WHERE p.isActivated = true AND p.startDate <= CURRENT_DATE AND p.endDate >= CURRENT_DATE AND p.id = :promotionId")
    Optional<Promotion> findAvailableById(@Param("promotionId") Integer promotionId);

    @Query("SELECT p FROM Promotion p WHERE p.isActivated = true AND p.startDate <= CURRENT_DATE AND p.endDate >= CURRENT_DATE AND (p.roomType.id = :roomTypeId OR p.roomType IS NULL)")
    Page<Promotion> findAllAvailable(@Param("roomTypeId") Integer roomTypeId, Pageable pageable);




}
