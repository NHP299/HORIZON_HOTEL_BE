package com.horizon.repository;

import com.horizon.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> getByAccountId(Integer accountId);

    Optional<Booking> findByPaymentId(Integer paymentId);
}
