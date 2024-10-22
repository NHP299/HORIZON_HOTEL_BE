package com.horizon.service.impl;

import com.horizon.domain.Media;
import com.horizon.domain.RoomType;
import com.horizon.dto.MediaDto;
import com.horizon.mapper.MediaMapper;
import com.horizon.repository.MediaRepository;
import com.horizon.repository.RoomTypeRepository;
import com.horizon.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Override
    public MediaDto storeMediaFile(MultipartFile file, Integer roomTypeId) throws IOException {
        RoomType roomType = roomTypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new RuntimeException("Room type not found"));

        Media media = new Media();
        media.setRoomType(roomType);
        media.setPath(file.getOriginalFilename());

        Media savedMedia = mediaRepository.save(media);
        return MediaMapper.INSTANCE.mediaToMediaDto(savedMedia);
    }

    @Override
    public List<MediaDto> getMediaByRoomType(Integer roomTypeId) {
        RoomType roomType = roomTypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new RuntimeException("Room type not found"));

        List<Media> mediaList = mediaRepository.findByRoomType(roomType);
        return MediaMapper.INSTANCE.mediaListToMediaDtoList(mediaList);
    }

    @Override
    public List<MediaDto> getAllMedia() {
        List<Media> mediaList = mediaRepository.findAll();
        return MediaMapper.INSTANCE.mediaListToMediaDtoList(mediaList);
    }

    @Override
    public void deleteMedia(Integer mediaId) {
        if (mediaRepository.existsById(mediaId)) {
            mediaRepository.deleteById(mediaId);
        } else {
            throw new RuntimeException("Media not found with ID: " + mediaId);
        }
    }
}
