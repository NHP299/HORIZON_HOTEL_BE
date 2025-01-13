package com.horizon.repository;

import com.horizon.domain.Services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Integer> {

    Page<Services> findByNameAndIsActivatedTrue(String name, Pageable pageable);

    Page<Services> findAllByIsActivatedTrue(Pageable pageable);

    Optional<Services> findByNameIgnoreCaseAndIsActivatedFalse(String name);

}
