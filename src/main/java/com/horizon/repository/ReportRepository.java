package com.horizon.repository;

import com.horizon.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReportRepository extends JpaRepository<Booking, Integer> {

    @Query("SELECT FUNCTION('TO_CHAR', b.checkIn, 'MM-YYYY') AS month, SUM(b.totalPrice) AS total_revenue " +
            "FROM Booking b " +
            "WHERE EXTRACT(MONTH FROM b.checkIn) = :month " +
            "AND EXTRACT(YEAR FROM b.checkIn) = :year " +
            "AND b.status = 4 " +
            "GROUP BY FUNCTION('TO_CHAR', b.checkIn, 'MM-YYYY')")
    List<Map<String, Object>> getMonthlyRevenueReport(@Param("month") int month, @Param("year") int year);

    @Query(value = "SELECT b.check_in AS booking_date, " +
            "COUNT(*) AS total_bookings, " +
            "SUM(b.total_price) AS total_revenue " +
            "FROM booking b " +
            "WHERE EXTRACT(MONTH FROM b.check_in) = :month " +
            "AND EXTRACT(YEAR FROM b.check_in) = :year " +
            "AND b.status = 4 " +
            "GROUP BY b.check_in " +
            "ORDER BY total_bookings DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Map<String, Object>> getTop5BookingDates(@Param("month") int month, @Param("year") int year);

    @Query(value = "SELECT rt.name AS room_type, " +
            "COUNT(bd.id) AS total_bookings " +
            "FROM room_type rt " +
            "LEFT JOIN room r ON rt.id = r.room_type_id " +
            "LEFT JOIN booking_detail bd ON r.id = bd.room_id " +
            "LEFT JOIN booking b ON bd.booking_id = b.id " +
            "WHERE EXTRACT(MONTH FROM b.check_in) = :month " +
            "AND EXTRACT(YEAR FROM b.check_in) = :year " +
            "AND b.status = 4 " +
            "GROUP BY rt.name " +
            "ORDER BY total_bookings DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Map<String, Object>> getTop5RoomTypeBookings(@Param("month") int month, @Param("year") int year);

    @Query(value = "SELECT r.id AS room_id, " +
            "r.name AS room_name, " +
            "rt.name AS room_type, " +
            "SUM(b.total_price) AS total_revenue, " +
            "COUNT(b.id) AS total_bookings " +
            "FROM room r " +
            "JOIN room_type rt ON r.room_type_id = rt.id " +
            "LEFT JOIN booking_detail bd ON r.id = bd.room_id " +
            "LEFT JOIN booking b ON bd.booking_id = b.id " +
            "WHERE EXTRACT(MONTH FROM b.check_in) = :month " +
            "AND EXTRACT(YEAR FROM b.check_in) = :year " +
            "AND b.status = 4 " +
            "GROUP BY r.id, r.name, rt.name " +
            "ORDER BY total_revenue DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Map<String, Object>> getTop5RoomBookings(@Param("month") int month, @Param("year") int year);

    @Query(value = "SELECT a.id AS account_id, " +
            "CONCAT(a.first_name, ' ', a.last_name) AS full_name, " +
            "a.email, " +
            "COUNT(b.id) AS total_bookings, " +
            "SUM(b.total_price) AS total_spent, " +
            "MAX(b.check_in) AS last_booking_date " +
            "FROM account a " +
            "LEFT JOIN booking b ON a.id = b.account_id " +
            "WHERE EXTRACT(MONTH FROM b.check_in) = :month " +
            "AND EXTRACT(YEAR FROM b.check_in) = :year " +
            "AND b.status = 4 " +
            "GROUP BY a.id, a.first_name, a.last_name, a.email " +
            "ORDER BY total_bookings DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Map<String, Object>> getTop5AccountsBySpending(@Param("month") int month, @Param("year") int year);


    @Query("SELECT COUNT(a) FROM Account a")
    long countTotalAccounts();

    @Query("SELECT COUNT(a) FROM Account a WHERE FUNCTION('DATE', a.createdDate) = CURRENT_DATE")
    long countNewAccountsToday();

    @Query("SELECT COUNT(a) FROM Account a WHERE a.isActivated = true")
    long countActivatedAccounts();

    @Query("SELECT ROUND(COUNT(a) FILTER (WHERE a.isActivated = true) * 100.0 / COUNT(a), 2) FROM Account a")
    double calculateActivatedPercentage();

    @Query("SELECT COUNT(r) FROM Room r")
    long countTotalRooms();

    @Query("SELECT COUNT(r) FROM Room r WHERE r.status = 0")
    long countActiveRooms();

    @Query("SELECT COUNT(r) FROM Room r WHERE r.status = 3")
    long countMaintenanceRooms();

    @Query("SELECT ROUND(COUNT(r) FILTER (WHERE r.status = 2) * 100.0 / COUNT(r), 2) FROM Room r")
    double calculateActiveRoomPercentage();


    @Query("SELECT SUM(b.totalPrice) FROM Booking b WHERE EXTRACT(MONTH FROM b.checkIn) = EXTRACT(MONTH FROM CURRENT_DATE) AND EXTRACT(YEAR FROM b.checkIn) = EXTRACT(YEAR FROM CURRENT_DATE) AND b.status = 4")
    Double getTotalRevenueCurrentMonth();

    @Query("SELECT COUNT(b) FROM Booking b WHERE EXTRACT(MONTH FROM b.checkIn) = EXTRACT(MONTH FROM CURRENT_DATE) AND EXTRACT(YEAR FROM b.checkIn) = EXTRACT(YEAR FROM CURRENT_DATE)")
    long countTotalBookingsCurrentMonth();

    @Query("SELECT rt.name AS roomType, COUNT(r.id) AS roomCount FROM Room r JOIN RoomType rt ON r.roomType.id = rt.id GROUP BY rt.name")
    List<Map<String, Object>> countRoomsByRoomType();

    @Query("SELECT EXTRACT(MONTH FROM b.checkIn) AS month, SUM(b.totalPrice) AS totalRevenue FROM Booking b WHERE b.status = 4 GROUP BY EXTRACT(MONTH FROM b.checkIn) ORDER BY month")
    List<Map<String, Object>> getMonthlyRevenue();
}

