package com.horizon.repository;

import com.horizon.domain.Utilities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilitiesRepository extends JpaRepository<Utilities, Integer> {

    Page<Utilities> findByNameAndIsActivatedTrue(String name, Pageable pageable);

    Page<Utilities> findAllByIsActivatedTrue(Pageable pageable);

    Optional<Utilities> findByNameIgnoreCaseAndIsActivatedFalse(String name);

}
