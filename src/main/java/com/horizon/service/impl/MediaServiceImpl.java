package com.horizon.service.impl;

import com.horizon.domain.Media;
import com.horizon.domain.RoomType;
import com.horizon.dto.MediaDto;
import com.horizon.exception.ExceptionHandlerService;
import com.horizon.mapper.MediaMapper;
import com.horizon.repository.MediaRepository;
import com.horizon.repository.RoomRepository;
import com.horizon.repository.RoomTypeRepository;
import com.horizon.service.CloudinaryService;
import com.horizon.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MediaMapper mediaMapper;

    @Autowired
    private ExceptionHandlerService exceptionHandlerService;

    @Autowired
    private CloudinaryService cloudinaryService;

    private Media getMediaByIdOrThrow(Integer mediaId) {
        return mediaRepository.findById(mediaId)
                .orElseThrow(() -> new RuntimeException("Media not found with ID: " + mediaId));
    }

    private RoomType getRoomTypeOrThrow(Integer roomTypeId) {
        return roomTypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new RuntimeException("Room type not found with ID: " + roomTypeId));
    }

    @Override
    public ResponseEntity<Map<String, Object>> createNewMedia(MultipartFile file, Integer roomTypeId) {
        if (file.isEmpty()) {
            return exceptionHandlerService.handleFileUploadError("File is empty.");
        }

        try {
            RoomType roomType = getRoomTypeOrThrow(roomTypeId);
            String publicId = cloudinaryService.uploadImage(file);

            Media media = new Media();
            media.setRoomType(roomType);
            media.setPublicId(publicId);
            media.setPath(cloudinaryService.getImageUrl(publicId));

            MediaDto mediaDto = mediaMapper.mediaToMediaDto(mediaRepository.save(media));
            return exceptionHandlerService.createResponse("Media uploaded successfully.", mediaDto, HttpStatus.CREATED);
        } catch (IOException e) {
            return exceptionHandlerService.handleFileUploadError("Error uploading image: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateMedia(Integer mediaId, MultipartFile file) {
        if (file.isEmpty()) {
            return exceptionHandlerService.handleFileUploadError("File is empty.");
        }

        try {
            Media existingMedia = getMediaByIdOrThrow(mediaId);

            String newPublicId = cloudinaryService.updateImage(file, existingMedia);

            existingMedia.setPublicId(newPublicId);
            existingMedia.setPath(cloudinaryService.getImageUrl(newPublicId));

            MediaDto updatedMediaDto = mediaMapper.mediaToMediaDto(existingMedia);

            mediaRepository.save(existingMedia);

            return exceptionHandlerService.createResponse("Media updated successfully.", updatedMediaDto, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandlerService.handleGeneralError("Error updating media: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteMedia(Integer mediaId) {
        try {
            Media media = getMediaByIdOrThrow(mediaId);

            String publicId = media.getPublicId();
            if (publicId == null || publicId.isEmpty()) {
                return exceptionHandlerService.handleGeneralError("Media does not have a valid publicId.");
            }

            cloudinaryService.deleteImage(publicId);
            mediaRepository.delete(media);

            return exceptionHandlerService.createResponse("Media deleted successfully.", null, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandlerService.handleGeneralError("Failed to delete media: " + e.getMessage());
        }
    }

    @Override
    public List<MediaDto> getMediaByRoomType(Integer roomTypeId) {
        RoomType roomType = getRoomTypeOrThrow(roomTypeId);
        return mediaMapper.mediaListToMediaDtoList(mediaRepository.findByRoomType(roomType));
    }

    @Override
    public List<MediaDto> getMediaByRoomName(String roomName) {
        RoomType roomType = roomRepository.findByName(roomName)
                .orElseThrow(() -> new RuntimeException("Room not found with name: " + roomName)).getRoomType();
        return mediaMapper.mediaListToMediaDtoList(mediaRepository.findByRoomType(roomType));
    }

    @Override
    public MediaDto getMediaById(Integer mediaId) {
        Media media = getMediaByIdOrThrow(mediaId);
        return mediaMapper.mediaToMediaDto(media);
    }

    @Override
    public List<MediaDto> getAllMedia() {
        return mediaMapper.mediaListToMediaDtoList(mediaRepository.findAll());
    }
}