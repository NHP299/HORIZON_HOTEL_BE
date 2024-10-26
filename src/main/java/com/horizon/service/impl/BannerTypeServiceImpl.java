package com.horizon.service.impl;

import com.horizon.domain.BannerType;
import com.horizon.dto.BannerTypeDto;
import com.horizon.repository.BannerTypeRepository;
import com.horizon.service.BannerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BannerTypeServiceImpl implements BannerTypeService {
    private final BannerTypeRepository bannerTypeRepository;

    @Autowired
    public BannerTypeServiceImpl(BannerTypeRepository bannerTypeRepository) {
        this.bannerTypeRepository = bannerTypeRepository;
    }

    @Override
    public String createBannerType(BannerTypeDto bannerTypeDto) {
        BannerType bannerType = new BannerType();
        bannerType.setName(bannerTypeDto.getName());
        BannerType savedBannerType = bannerTypeRepository.save(bannerType);
        return "Banner Type created successfully with ID: " + savedBannerType.getId();
    }

    @Override
    public String updateBannerType(Integer id, BannerTypeDto bannerTypeDto) {
        BannerType bannerType = bannerTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BannerType not found with id: " + id));
        bannerType.setName(bannerTypeDto.getName());
        BannerType updatedBannerType = bannerTypeRepository.save(bannerType);
        return "Banner Type updated successfully with ID: " + updatedBannerType.getId();
    }

    @Override
    public String deleteBannerType(Integer id) {
        if (!bannerTypeRepository.existsById(id)) {
            throw new RuntimeException("BannerType not found with id: " + id);
        }
        bannerTypeRepository.deleteById(id);
        return "Banner Type deleted successfully with ID: " + id;
    }

    @Override
    public List<BannerTypeDto> getAllBannerTypes() {
        return bannerTypeRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BannerTypeDto getBannerTypeById(Integer id) {
        BannerType bannerType = bannerTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BannerType not found with id: " + id));
        return convertToDto(bannerType);
    }

    private BannerTypeDto convertToDto(BannerType bannerType) {
        return new BannerTypeDto(bannerType.getId(), bannerType.getName());
    }
}
