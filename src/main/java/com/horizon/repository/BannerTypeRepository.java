package com.horizon.repository;

import com.horizon.domain.BannerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerTypeRepository extends JpaRepository<BannerType, Integer> {

}
