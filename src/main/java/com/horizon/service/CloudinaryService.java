package com.horizon.service;

import com.horizon.domain.Media;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {
    String uploadImage(MultipartFile file) throws IOException;

    void deleteImage(String publicId) throws IOException;

    String updateImage(MultipartFile file, Media media) throws IOException;

    String getImageUrl(String publicId);
}