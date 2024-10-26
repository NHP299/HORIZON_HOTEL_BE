package com.horizon.service.impl;

import com.horizon.domain.Media;
import com.horizon.domain.RoomType;
import com.horizon.dto.MediaDto;
import com.horizon.mapper.MediaMapper; // Import the MediaMapper interface
import com.horizon.repository.MediaRepository;
import com.horizon.repository.RoomTypeRepository;
import com.horizon.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MediaServiceImpl implements MediaService {

    @Value("${media.upload.dir}")
    private String uploadDir;

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private MediaMapper mediaMapper;

    // Upload new image
    @Override
    public ResponseEntity<Map<String, Object>> saveNewMedia(MultipartFile file, Integer roomTypeId) {
        Map<String, Object> response = new HashMap<>();

        if (file.isEmpty()) {
            response.put("message", "File is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            RoomType roomType = roomTypeRepository.findById(roomTypeId)
                    .orElseThrow(() -> new RuntimeException("Room type not found"));

            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, filename);
            Files.copy(file.getInputStream(), filePath);

            Media media = new Media();
            media.setRoomType(roomType);
            media.setPath(filename);

            Media savedMedia = mediaRepository.save(media);
            MediaDto mediaDto = mediaMapper.mediaToMediaDto(savedMedia);

            response.put("message", "Media uploaded successfully.");
            response.put("media", mediaDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("message", "An error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Update image by mediaId and roomTypeId
    @Override
    public ResponseEntity<Map<String, Object>> updateMedia(Integer mediaId, MultipartFile file) {
        Map<String, Object> response = new HashMap<>();

        if (file.isEmpty()) {
            response.put("message", "File is empty.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            Media media = mediaRepository.findById(mediaId)
                    .orElseThrow(() -> new RuntimeException("Media not found with ID: " + mediaId));

            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, filename);
            Files.copy(file.getInputStream(), filePath);

            media.setPath(filename);

            Media updatedMedia = mediaRepository.save(media);
            MediaDto mediaDto = mediaMapper.mediaToMediaDto(updatedMedia);

            response.put("message", "Media updated successfully.");
            response.put("media", mediaDto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            response.put("message", "An error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    // Delete image by mediaId
    @Override
    public ResponseEntity<Map<String, Object>> deleteMedia(Integer mediaId) {
        Map<String, Object> response = new HashMap<>();

        return mediaRepository.findById(mediaId)
                .map(media -> {
                    try {
                        Path filePath = Paths.get(uploadDir, media.getPath());
                        Files.deleteIfExists(filePath);
                    } catch (Exception e) {
                        response.put("message", "Failed to delete file from directory: " + e.getMessage());
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
                    }

                    mediaRepository.delete(media);
                    response.put("message", "Media deleted successfully.");
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                }).orElseGet(() -> {
                    response.put("message", "Media not found with ID: " + mediaId);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                });
    }

    // Get image by roomTypeId
    @Override
    public List<MediaDto> getMediaByRoomType(Integer roomTypeId) {
        RoomType roomType = roomTypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new RuntimeException("Room type not found"));

        List<Media> mediaList = mediaRepository.findByRoomType(roomType);
        return mediaMapper.mediaListToMediaDtoList(mediaList);
    }

    // Get all images
    @Override
    public List<MediaDto> getAllMedia() {
        List<Media> mediaList = mediaRepository.findAll();
        return mediaMapper.mediaListToMediaDtoList(mediaList);
    }
}
