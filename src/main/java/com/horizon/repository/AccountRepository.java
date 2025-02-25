package com.horizon.repository;

import com.horizon.domain.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> getByIdAndIsActivatedTrue(Integer id);

    Optional<Account> findByEmail(String email);

    Page<Account> findAllByIsActivatedTrue(Pageable pageable);
}
