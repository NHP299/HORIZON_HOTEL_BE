package com.horizon.repository;

import com.horizon.domain.BannerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BannerTypeRepository extends JpaRepository<BannerType, Integer> {
    Optional<BannerType> findByName(String name);  // Thêm phương thức này
}
