package com.horizon.mapper;

import com.horizon.domain.Media;
import com.horizon.dto.MediaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MediaMapper {
    MediaMapper INSTANCE = Mappers.getMapper(MediaMapper.class);

    @Mapping(source = "roomType.id", target = "roomTypeId")
    MediaDto mediaToMediaDto(Media media);

//    @Mapping(source = "roomTypeId", target = "roomType.id")
//    Media mediaDtoToMedia(MediaDto mediaDto);

    List<MediaDto> mediaListToMediaDtoList(List<Media> mediaList);
}
