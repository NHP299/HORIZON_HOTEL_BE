package com.horizon.repository;

import com.horizon.domain.Promotion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    Optional<Promotion> findByIdAndStatus(Integer id, Promotion.Status status);
    List<Promotion> findByNameContainingIgnoreCaseAndStatus(String name, Promotion.Status status);
    List<Promotion> findAllByStatus(Promotion.Status status);


    @Query("SELECT p FROM Promotion p WHERE p.status = 'ACTIVE' AND p.startDate <= CURRENT_DATE AND p.endDate >= CURRENT_DATE AND p.id = :promotionId")
    Promotion findAvailableById(@Param("promotionId") Integer promotionId);

    @Query("SELECT p FROM Promotion p WHERE p.status = 'ACTIVE' AND p.startDate <= CURRENT_DATE AND p.endDate >= CURRENT_DATE AND (p.roomType.id = :roomTypeId OR p.roomType IS NULL)")
    List<Promotion> findAllAvailable(@Param("roomTypeId") Integer roomTypeId);

    @Transactional
    @Modifying
    @Query("UPDATE Promotion p SET p.status = 'INACTIVE' WHERE (p.endDate < CURRENT_DATE OR p.startDate > CURRENT_DATE) AND p.status = 'ACTIVE'")
    void updateStatusIfExpired();



}
