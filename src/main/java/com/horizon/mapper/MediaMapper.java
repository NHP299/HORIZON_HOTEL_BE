package com.horizon.mapper;

import com.horizon.domain.Media;
import com.horizon.dto.MediaDto;

import java.util.List;

public interface MediaMapper {
    MediaDto mediaToMediaDto(Media media);
    Media mediaDtoToMedia(MediaDto mediaDto);
    List<MediaDto> mediaListToMediaDtoList(List<Media> mediaList);
}
