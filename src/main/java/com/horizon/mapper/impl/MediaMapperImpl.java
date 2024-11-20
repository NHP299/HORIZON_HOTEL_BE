package com.horizon.mapper.impl;

import com.horizon.domain.Media;
import com.horizon.dto.MediaDto;
import com.horizon.mapper.MediaMapper;
import com.horizon.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MediaMapperImpl implements MediaMapper {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public MediaDto toDto(Media media) {
        if (media == null) {
            return null;
        }

        MediaDto mediaDto = new MediaDto();
        mediaDto.setId(media.getId());
        mediaDto.setPath(media.getPath());
        mediaDto.setRoomTypeId(media.getRoomType().getId());

        mediaDto.setPublicId(cloudinaryService.getPublicId(media.getPath()));

        return mediaDto;
    }

    @Override
    public Media toEntity(MediaDto mediaDto) {
        if (mediaDto == null) {
            return null;
        }

        Media media = new Media();
        media.setId(mediaDto.getId());
        media.setPath(mediaDto.getPath());

        return media;
    }
}
