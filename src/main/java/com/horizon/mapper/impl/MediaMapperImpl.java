package com.horizon.mapper.impl;

import com.horizon.domain.Media;
import com.horizon.dto.MediaDto;
import com.horizon.mapper.MediaMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MediaMapperImpl implements MediaMapper {

    @Override
    public MediaDto mediaToMediaDto(Media media) {
        if (media == null) {
            return null;
        }

        MediaDto mediaDto = new MediaDto();
        mediaDto.setId(media.getId());
        mediaDto.setRoomTypeId(media.getRoomType().getId());
        mediaDto.setPath(media.getPath());
        return mediaDto;
    }

    @Override
    public Media mediaDtoToMedia(MediaDto mediaDto) {
        if (mediaDto == null) {
            return null;
        }

        Media media = new Media();
        media.setId(mediaDto.getId());
        media.setPath(mediaDto.getPath());
        return media;
    }

    @Override
    public List<MediaDto> mediaListToMediaDtoList(List<Media> mediaList) {
        if (mediaList == null) {
            return null;
        }

        return mediaList.stream()
                .map(this::mediaToMediaDto)
                .collect(Collectors.toList());
    }
}
