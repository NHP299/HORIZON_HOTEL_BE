package com.horizon.service.impl;

import com.horizon.domain.Media;
import com.horizon.domain.RoomType;
import com.horizon.dto.MediaDto;
import com.horizon.mapper.MediaMapper;
import com.horizon.repository.MediaRepository;
import com.horizon.repository.RoomTypeRepository;
import com.horizon.service.CloudinaryService;
import com.horizon.service.MediaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;

    private final RoomTypeRepository roomTypeRepository;

    private final CloudinaryService cloudinaryService;

    private final MediaMapper mediaMapper;

    @Override
    public MediaDto create(MultipartFile file, Integer roomTypeId) {
        RoomType roomType = getRoomType(roomTypeId);
        String path = uploadFile(file);

        Media media = new Media();
        media.setPath(path);
        media.setRoomType(roomType);
        media = mediaRepository.save(media);

        return mediaMapper.toDto(media);
    }

    @Override
    public MediaDto update(Long id, MultipartFile file) {
        Media media = getMedia(id);
        String oldPublicId = cloudinaryService.getPublicId(media.getPath());
        validatePublicId(oldPublicId);

        String newPath = uploadFile(file);
        cloudinaryService.delete(oldPublicId);

        media.setPath(newPath);
        media = mediaRepository.save(media);

        return mediaMapper.toDto(media);
    }

    @Override
    public void delete(Long id) {
        Media media = getMedia(id);
        String publicId = cloudinaryService.getPublicId(media.getPath());
        cloudinaryService.delete(publicId);

        mediaRepository.delete(media);
    }

    @Override
    public List<MediaDto> getAll() {
        return mediaRepository.findAll().stream()
                .map(mediaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<MediaDto> getAll(Pageable pageable) {
        return mediaRepository.findAll(pageable)
                .map(mediaMapper::toDto);
    }

    @Override
    public Page<MediaDto> getByRoomTypeId(Integer roomTypeId, Pageable pageable) {
        RoomType roomType = getRoomType(roomTypeId);
        Page<Media> mediaList = mediaRepository.findByRoomType(roomType, pageable);
        return mediaList.map(mediaMapper::toDto);
    }

    @Override
    public Page<MediaDto> getByRoomTypeName(String roomName, Pageable pageable) {
        RoomType roomType = roomTypeRepository.findByName(roomName)
                .orElseThrow(() -> new RuntimeException("RoomType with name " + roomName + " not found"));
        Page<Media> mediaList = mediaRepository.findByRoomType(roomType, pageable);
        return mediaList.map(mediaMapper::toDto);
    }

    @Override
    public MediaDto getById(Long id) {
        Media media = getMedia(id);
        return mediaMapper.toDto(media);
    }

    private RoomType getRoomType(Integer roomTypeId) {
        return roomTypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new RuntimeException("RoomType not found"));
    }

    private Media getMedia(Long id) {
        return mediaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Media not found"));
    }

    private String uploadFile(MultipartFile file) {
        return cloudinaryService.upload(file);
    }

    private void validatePublicId(String publicId) {
        if (publicId == null || publicId.isEmpty()) {
            throw new RuntimeException("The old image public ID is invalid.");
        }
    }
}
