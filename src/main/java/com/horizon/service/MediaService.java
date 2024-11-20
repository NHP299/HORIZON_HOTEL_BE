package com.horizon.service;


import com.horizon.dto.MediaDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {
    MediaDto create(MultipartFile file, Integer roomTypeId);
    MediaDto update(Long id, MultipartFile file);
    void delete(Long id);
    MediaDto getById(Long id);
    List<MediaDto> getByRoomTypeId(Integer roomTypeId);
    List<MediaDto> getByRoomName(String roomName);
    List<MediaDto> getAll();
}

