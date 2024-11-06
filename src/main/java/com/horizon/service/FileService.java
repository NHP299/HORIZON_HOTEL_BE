package com.horizon.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String saveFile(MultipartFile file, String uploadDir) throws Exception;
    void deleteFile(String filename, String uploadDir) throws Exception;
}
