package com.horizon.service;


import com.horizon.dto.MediaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {
    MediaDto create(MultipartFile file, Integer roomTypeId);
    MediaDto update(Long id, MultipartFile file);
    void delete(Long id);
    MediaDto getById(Long id);
    List<MediaDto> getAll();
    Page<MediaDto> getAll(Pageable pageable);
    Page<MediaDto> getByRoomTypeId(Integer roomTypeId, Pageable pageable);
    Page<MediaDto> getByRoomTypeName(String roomName, Pageable pageable);
}

