package com.horizon.repository;

import com.horizon.domain.Utilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilitiesRepository extends JpaRepository<Utilities, Integer> {
    List<Utilities> findByNameContainingIgnoreCase(String name);

}
