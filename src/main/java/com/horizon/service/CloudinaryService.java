package com.horizon.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    String upload(MultipartFile file);
    void delete(String publicId);
    String getPublicId(String path);
}
