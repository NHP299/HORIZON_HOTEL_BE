package com.horizon.repository;

import com.horizon.domain.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    List<Promotion> findByNameContainingIgnoreCase(String name);

    @Query("SELECT p FROM Promotion p WHERE p.id = :promotionId AND CURRENT_TIMESTAMP BETWEEN p.startTime AND p.endTime AND p.maxUsage > 0")
    Optional<Promotion> findByIdAndAvailable(@Param("promotionId") Integer promotionId);

    @Query("SELECT p FROM Promotion p WHERE CURRENT_TIMESTAMP BETWEEN p.startTime AND p.endTime AND p.maxUsage > 0")
    List<Promotion> findAllAvailable();

    @Query("SELECT p FROM Promotion p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :promotionName, '%')) AND CURRENT_TIMESTAMP BETWEEN p.startTime AND p.endTime AND p.maxUsage > 0")
    List<Promotion> findByNameContainingAndAvailable(@Param("promotionName") String promotionName);

    @Query("SELECT p FROM Promotion p " +
            "JOIN p.promotionCondition pc " +
            "JOIN pc.promotionType pt " +
            "WHERE " +
            "(CURRENT_TIMESTAMP BETWEEN p.startTime AND p.endTime AND p.maxUsage > 0) AND " +
            "(" +
            "   (pt.name = 'days_of_booking' AND :daysOfBooking >= pc.value) OR " +
            "   (pt.name = 'rooms_of_booking' AND :roomsOfBooking >= pc.value) OR " +
            "   (pt.name = 'booking_time_range' AND CURRENT_TIME BETWEEN pc.startTime AND pc.endTime)" +
            ")")
    List<Promotion> findApplicable(@Param("daysOfBooking") int daysOfBooking,
                                             @Param("roomsOfBooking") int roomsOfBooking);

    @Modifying
    @Query("UPDATE Promotion p SET p.maxUsage = p.maxUsage - 1 WHERE p.id = :promotionId")
    int decrementMaxUsage(@Param("promotionId") Integer promotionId);
}
