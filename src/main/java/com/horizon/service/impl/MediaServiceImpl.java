package com.horizon.service.impl;

import com.horizon.domain.Media;
import com.horizon.domain.RoomType;
import com.horizon.dto.MediaDto;
import com.horizon.mapper.MediaMapper;
import com.horizon.repository.MediaRepository;
import com.horizon.repository.RoomTypeRepository;
import com.horizon.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    //upload new image
    @Override
    public ResponseEntity<Map<String, Object>> storeMediaFile(MultipartFile file, Integer roomTypeId) {
        Map<String, Object> response = new HashMap<>();

        if (file.isEmpty()) {
            response.put("message", "File is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            RoomType roomType = roomTypeRepository.findById(roomTypeId)
                    .orElseThrow(() -> new RuntimeException("Room type not found"));

            Media media = new Media();
            media.setRoomType(roomType);
            media.setPath(file.getOriginalFilename());

            Media savedMedia = mediaRepository.save(media);
            MediaDto mediaDto = MediaMapper.INSTANCE.mediaToMediaDto(savedMedia);

            response.put("message", "Media uploaded successfully.");
            response.put("media", mediaDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("message", "An error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //update image by mediaId and roomTypeRoom corresponding to image
    @Override
    public ResponseEntity<Map<String, Object>> updateMedia(Integer mediaId, MultipartFile file, Integer roomTypeId) {
        Map<String, Object> response = new HashMap<>();

        if (file.isEmpty()) {
            response.put("message", "File is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            Media media = mediaRepository.findById(mediaId)
                    .orElseThrow(() -> new RuntimeException("Media not found with ID: " + mediaId));

            RoomType roomType = roomTypeRepository.findById(roomTypeId)
                    .orElseThrow(() -> new RuntimeException("Room type not found"));

            media.setRoomType(roomType);
            media.setPath(file.getOriginalFilename());

            Media updatedMedia = mediaRepository.save(media);
            MediaDto mediaDto = MediaMapper.INSTANCE.mediaToMediaDto(updatedMedia);

            response.put("message", "Media updated successfully.");
            response.put("media", mediaDto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RuntimeException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("message", "An error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //delete image by mediaId
    @Override
    public ResponseEntity<Map<String, Object>> deleteMedia(Integer mediaId) {
        Map<String, Object> response = new HashMap<>();

        return mediaRepository.findById(mediaId)
                .map(media -> {
                    mediaRepository.delete(media);
                    response.put("message", "Media deleted successfully.");
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                }).orElseGet(() -> {
                    response.put("message", "Media not found with ID: " + mediaId);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                });
    }

    //get image by roomTypeId
    @Override
    public List<MediaDto> getMediaByRoomType(Integer roomTypeId) {
        RoomType roomType = roomTypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new RuntimeException("Room type not found"));

        List<Media> mediaList = mediaRepository.findByRoomType(roomType);
        return MediaMapper.INSTANCE.mediaListToMediaDtoList(mediaList);
    }

    //get all image
    @Override
    public List<MediaDto> getAllMedia() {
        List<Media> mediaList = mediaRepository.findAll();
        return MediaMapper.INSTANCE.mediaListToMediaDtoList(mediaList);
    }

}
