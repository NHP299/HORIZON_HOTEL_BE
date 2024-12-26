package com.horizon.repository;

import com.horizon.domain.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Integer> {

    List<Services> findByNameContainingIgnoreCaseAndIsActivatedTrue(String name);

    List<Services> findAllByIsActivatedTrue();

    Optional<Services> findByNameIgnoreCaseAndIsActivatedFalse(String name);

}
