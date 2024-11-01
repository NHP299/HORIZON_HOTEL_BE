package com.horizon.service.impl;

import com.horizon.domain.Banner;
import com.horizon.domain.BannerType;
import com.horizon.dto.BannerDto;
import com.horizon.exception.ExceptionHandlerService;
import com.horizon.mapper.BannerMapper;
import com.horizon.repository.BannerRepository;
import com.horizon.repository.BannerTypeRepository;
import com.horizon.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Service
public class BannerServiceImpl implements BannerService {

    @Value("${banner.upload.dir}")
    private String uploadDir;

    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private BannerTypeRepository bannerTypeRepository;

    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private ExceptionHandlerService exceptionHandlerService;

    private String saveFile(MultipartFile file) throws Exception {
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, filename);
        Files.copy(file.getInputStream(), filePath);
        return filename;
    }

    private void deleteFile(String filename) throws Exception {
        Path filePath = Paths.get(uploadDir, filename);
        if (Files.exists(filePath)) {
            Files.delete(filePath);
        }
    }

    private Banner getBannerByIdOrThrow(Integer bannerId) {
        return bannerRepository.findById(bannerId)
                .orElseThrow(() -> new RuntimeException("Banner not found with ID: " + bannerId));
    }

    private BannerType getBannerTypeOrThrow(Integer bannerTypeId) {
        return bannerTypeRepository.findById(bannerTypeId)
                .orElseThrow(() -> new RuntimeException("Banner type not found with ID: " + bannerTypeId));
    }

    @Override
    public ResponseEntity<Map<String, Object>> createNewBanner(MultipartFile file, BannerDto bannerDto) {
        if (file.isEmpty()) {
            return exceptionHandlerService.handleFileUploadError("File is empty.");
        }

        try {
            String filename = saveFile(file);
            Banner banner = new Banner();
            banner.setName(bannerDto.getName());
            banner.setDescription(bannerDto.getDescription());
            banner.setPath(filename);
            banner.setBannerType(getBannerTypeOrThrow(bannerDto.getBannerTypeId()));

            BannerDto savedBannerDto = bannerMapper.bannerToBannerDto(bannerRepository.save(banner));
            return exceptionHandlerService.createResponse("Banner uploaded successfully.", savedBannerDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return exceptionHandlerService.handleGeneralError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateBanner(Integer bannerId, MultipartFile file, BannerDto bannerDto) {
        return executeMediaOperation(() -> {
            Banner banner = getBannerByIdOrThrow(bannerId);
            if (file != null && !file.isEmpty()) {
                deleteFile(banner.getPath()); // Xóa file cũ nếu có file mới được tải lên
                String filename = saveFile(file);
                banner.setPath(filename);
            }

            // Cập nhật các thông tin khác
            banner.setName(bannerDto.getName());
            banner.setDescription(bannerDto.getDescription());

            // Cập nhật loại banner nếu cần
            if (bannerDto.getBannerTypeId() != null) {
                BannerType bannerType = getBannerTypeOrThrow(bannerDto.getBannerTypeId());
                banner.setBannerType(bannerType);
            }

            BannerDto updatedBannerDto = bannerMapper.bannerToBannerDto(bannerRepository.save(banner));
            return exceptionHandlerService.createResponse("Banner updated successfully.", updatedBannerDto, HttpStatus.OK);
        });
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteBanner(Integer bannerId) {
        return executeMediaOperation(() -> {
            Banner banner = getBannerByIdOrThrow(bannerId);
            deleteFile(banner.getPath());
            bannerRepository.delete(banner);
            return exceptionHandlerService.createResponse("Banner deleted successfully.", null, HttpStatus.OK);
        });
    }

    @Override
    public BannerDto getBannerById(Integer bannerId) {
        Banner banner = getBannerByIdOrThrow(bannerId);
        return bannerMapper.bannerToBannerDto(banner);
    }

    @Override
    public List<BannerDto> getAllBanners() {
        return bannerMapper.bannerListToBannerDtoList(bannerRepository.findAll());
    }

    private ResponseEntity<Map<String, Object>> executeMediaOperation(BannerOperation operation) {
        try {
            return operation.perform();
        } catch (Exception e) {
            return exceptionHandlerService.handleGeneralError(e.getMessage());
        }
    }

    @FunctionalInterface
    interface BannerOperation {
        ResponseEntity<Map<String, Object>> perform() throws Exception;
    }
}
