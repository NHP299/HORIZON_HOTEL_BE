package com.horizon.service;

import com.horizon.dto.MediaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface MediaService {
    ResponseEntity<Map<String, Object>> createNewMedia(MultipartFile file, Integer roomTypeId);
    ResponseEntity<Map<String, Object>> deleteMedia(Integer mediaId);
    ResponseEntity<Map<String, Object>> updateMedia(Integer mediaId, MultipartFile file);
    List<MediaDto> getMediaByRoomType(Integer roomTypeId);
    List<MediaDto> getMediaByRoomName(String roomName);
    MediaDto getMediaById(Integer mediaId);
    List<MediaDto> getAllMedia();
}