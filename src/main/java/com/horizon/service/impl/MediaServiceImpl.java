package com.horizon.service.impl;

import com.horizon.domain.Media;
import com.horizon.domain.RoomType;
import com.horizon.dto.MediaDto;
import com.horizon.exception.ExceptionHandlerService;
import com.horizon.mapper.MediaMapper;
import com.horizon.repository.MediaRepository;
import com.horizon.repository.RoomRepository;
import com.horizon.repository.RoomTypeRepository;
import com.horizon.service.FileService;
import com.horizon.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    private RoomRepository roomRepository;

    @Autowired
    private MediaMapper mediaMapper;

    @Autowired
    private ExceptionHandlerService exceptionHandlerService;

    @Autowired
    private FileService fileService;

    private Media getMediaByIdOrThrow(Integer mediaId) {
        return mediaRepository.findById(mediaId)
                .orElseThrow(() -> new RuntimeException("Media not found with ID: " + mediaId));
    }

    private RoomType getRoomTypeOrThrow(Integer roomTypeId) {
        return roomTypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new RuntimeException("Room type not found with ID: " + roomTypeId));
    }

    private ResponseEntity<Map<String, Object>> executeMediaOperation(MediaOperation operation) {
        try {
            return operation.perform();
        } catch (Exception e) {
            return exceptionHandlerService.handleGeneralError(e.getMessage());
        }
    }

    @FunctionalInterface
    interface MediaOperation {
        ResponseEntity<Map<String, Object>> perform() throws Exception;
    }

    @Override
    public ResponseEntity<Map<String, Object>> createNewMedia(MultipartFile file, Integer roomTypeId) {
        if (file.isEmpty()) {
            return exceptionHandlerService.handleFileUploadError("File is empty.");
        }

        return executeMediaOperation(() -> {
            RoomType roomType = getRoomTypeOrThrow(roomTypeId);
            String filename = fileService.saveFile(file, uploadDir);

            Media media = new Media();
            media.setRoomType(roomType);
            media.setPath(filename);

            MediaDto mediaDto = mediaMapper.mediaToMediaDto(mediaRepository.save(media));

            return exceptionHandlerService.createResponse("Media uploaded successfully.", mediaDto, HttpStatus.CREATED);
        });
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateMedia(Integer mediaId, MultipartFile file) {
        if (file.isEmpty()) {
            return exceptionHandlerService.handleFileUploadError("File is empty.");
        }

        return executeMediaOperation(() -> {
            Media media = getMediaByIdOrThrow(mediaId);
            fileService.deleteFile(media.getPath(), uploadDir);

            String filename = fileService.saveFile(file, uploadDir);
            media.setPath(filename);

            MediaDto updatedMediaDto = mediaMapper.mediaToMediaDto(mediaRepository.save(media));

            return exceptionHandlerService.createResponse("Media updated successfully.", updatedMediaDto, HttpStatus.OK);
        });
    }

    @Override
    public ResponseEntity<Map<String, Object>> deleteMedia(Integer mediaId) {
        return executeMediaOperation(() -> {
            Media media = getMediaByIdOrThrow(mediaId);
            fileService.deleteFile(media.getPath(), uploadDir);
            mediaRepository.delete(media);
            return exceptionHandlerService.createResponse("Media deleted successfully.", null, HttpStatus.OK);
        });
    }

    @Override
    public List<MediaDto> getMediaByRoomName(String roomName) {
        RoomType roomType = roomRepository.findByName(roomName)
                .orElseThrow(() -> new RuntimeException("Room not found with name: " + roomName)).getRoomType();
        return mediaMapper.mediaListToMediaDtoList(mediaRepository.findByRoomType(roomType));
    }

    @Override
    public List<MediaDto> getMediaByRoomType(Integer roomTypeId) {
        RoomType roomType = getRoomTypeOrThrow(roomTypeId);
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
