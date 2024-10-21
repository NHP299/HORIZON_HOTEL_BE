package com.horizon.mapper;

import com.horizon.domain.Room;
import com.horizon.dto.RoomDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoomMapper {
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    @Mapping(source = "roomType.id", target = "roomTypeId")
    RoomDto toRoomDto(Room room);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "roomTypeId", target = "roomType.id")
    Room toRoom(RoomDto roomDto);
}
