package com.horizon.service;

import com.horizon.dto.MediaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public interface MediaService {

    ResponseEntity<Map<String, Object>> storeMediaFile(MultipartFile file, Integer roomTypeId);
    ResponseEntity<Map<String, Object>> deleteMedia(Integer mediaId);
    ResponseEntity<Map<String, Object>> updateMedia(Integer mediaId, MultipartFile file, Integer roomTypeId);
    List<MediaDto> getMediaByRoomType(Integer roomTypeId);
    List<MediaDto> getAllMedia();
}
