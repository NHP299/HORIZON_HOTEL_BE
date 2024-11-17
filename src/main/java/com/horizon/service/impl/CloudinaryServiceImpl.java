package com.horizon.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.horizon.domain.Media;
import com.horizon.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return (String) uploadResult.get("public_id");
    }

    public void deleteImage(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new RuntimeException("Could not delete image with public ID: " + publicId, e);
        }
    }

    public String getImageUrl(String publicId) {
        return cloudinary.url().publicId(publicId).generate();
    }

    public String updateImage(MultipartFile file, Media media) throws IOException {
        if (media == null || media.getPublicId() == null) {
            throw new IllegalArgumentException("Media object and its public ID are required to update the image.");
        }
        String oldPublicId = media.getPublicId();
        deleteImage(oldPublicId);
        return uploadImage(file);
    }
}