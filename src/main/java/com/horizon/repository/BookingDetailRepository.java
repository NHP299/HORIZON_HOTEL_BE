package com.horizon.repository;

import com.horizon.domain.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingDetailRepository extends JpaRepository<BookingDetail, Integer> {

    List<BookingDetail> findByBookingId(Integer bookingId);
}
