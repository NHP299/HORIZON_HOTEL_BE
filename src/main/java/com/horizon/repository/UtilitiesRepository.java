package com.horizon.repository;

import com.horizon.domain.Utilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilitiesRepository extends JpaRepository<Utilities, Integer> {

    List<Utilities> findByNameContainingIgnoreCaseAndIsActivatedTrue(String name);

    List<Utilities> findAllByIsActivatedTrue();

    Optional<Utilities> findByNameIgnoreCaseAndIsActivatedFalse(String name);

}
