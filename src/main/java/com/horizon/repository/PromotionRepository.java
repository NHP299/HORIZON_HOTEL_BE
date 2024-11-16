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
    List<Promotion> findByNameContainingIgnoreCase(String name);

    @Query("SELECT p FROM Promotion p WHERE p.id = :promotionId AND CURRENT_TIMESTAMP BETWEEN p.startTime AND p.endTime")
    Optional<Promotion> findPromotionByIdAndAvailable(@Param("promotionId") Integer promotionId);

    @Query("SELECT p FROM Promotion p WHERE CURRENT_TIMESTAMP BETWEEN p.startTime AND p.endTime")
    List<Promotion> findAllAvailablePromotions();

    @Query("SELECT p FROM Promotion p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :promotionName, '%')) AND CURRENT_TIMESTAMP BETWEEN p.startTime AND p.endTime")
    List<Promotion> findPromotionByNameContainingAndAvailable(@Param("promotionName") String promotionName);

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
