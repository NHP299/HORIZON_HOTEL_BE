package com.horizon.service.impl;

import com.horizon.domain.BannerType;
import com.horizon.dto.BannerTypeDto;
import com.horizon.mapper.BannerTypeMapper;
import com.horizon.repository.BannerTypeRepository;
import com.horizon.service.BannerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BannerTypeServiceImpl implements BannerTypeService {
    private final BannerTypeRepository bannerTypeRepository;
    private final BannerTypeMapper bannerTypeMapper;

    @Autowired
    public BannerTypeServiceImpl(BannerTypeRepository bannerTypeRepository, BannerTypeMapper bannerTypeMapper) {
        this.bannerTypeRepository = bannerTypeRepository;
        this.bannerTypeMapper = bannerTypeMapper;
    }

    @Override
    public String createBannerType(BannerTypeDto bannerTypeDto) {
        BannerType bannerType = bannerTypeMapper.toEntity(bannerTypeDto);
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
                .map(bannerTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BannerTypeDto getBannerTypeById(Integer id) {
        BannerType bannerType = bannerTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BannerType not found with id: " + id));
        return bannerTypeMapper.toDto(bannerType);
    }
}
