package com.horizon.repository;

import com.horizon.domain.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Query("SELECT p FROM Promotion p " +
            "JOIN p.promotionCondition pc " +
            "JOIN pc.promotionType pt " +
            "WHERE " +
            "(CURRENT_TIMESTAMP BETWEEN p.startTime AND p.endTime) AND " +
            "(" +
            "   (pt.name = 'days_of_booking' AND :daysOfBooking >= pc.value) OR " +
            "   (pt.name = 'rooms_of_booking' AND :roomsOfBooking >= pc.value) OR " +
            "   (pt.name = 'booking_time_range' AND CURRENT_TIME BETWEEN pc.startTime AND pc.endTime)" +
            ")")
    List<Promotion> findApplicablePromotions(@Param("daysOfBooking") int daysOfBooking,
                                             @Param("roomsOfBooking") int roomsOfBooking);

}
