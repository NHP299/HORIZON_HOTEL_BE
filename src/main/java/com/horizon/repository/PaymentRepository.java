package com.horizon.repository;

import com.horizon.domain.Payment;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment findByTransactionId(String transactionId);

    @Query("SELECT p FROM Payment p WHERE p.expiredTime < :currentTime AND p.status = com.horizon.domain.Payment.Status.PENDING ")
    List<Payment> findExpiredPayments(@Param("currentTime") Timestamp currentTime);

    @NotNull
    Page<Payment> findAll(@NotNull Pageable pageable);
}
