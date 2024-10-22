package com.horizon.service;

import com.horizon.dto.MediaDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface MediaService {

    MediaDto storeMediaFile(MultipartFile file, Integer roomTypeId) throws IOException;
    List<MediaDto> getMediaByRoomType(Integer roomTypeId);
    List<MediaDto> getAllMedia();
    void deleteMedia(Integer mediaId);
}
