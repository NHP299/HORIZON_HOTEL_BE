package com.horizon.service.impl;

import com.horizon.domain.RoomType;
import com.horizon.dto.RoomTypeDto;
import com.horizon.mapper.RoomTypeMapper;
import com.horizon.repository.RoomTypeRepository;
import com.horizon.service.RoomTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomTypeServiceImpl implements RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;
    private RoomTypeMapper roomTypeMapper;


    //Create room type
    @Override
    public RoomTypeDto createRoomType(RoomTypeDto roomTypeDto) {
        RoomType roomType = roomTypeMapper.mapToRoomType(roomTypeDto);
        RoomType saveRoomType = roomTypeRepository.save(roomType);
        return roomTypeMapper.mapToRoomTypeDto(saveRoomType);
    }

    //Get room type by id
    @Override
    public RoomTypeDto getRoomTypeById(Integer id) {
        RoomType roomType = roomTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Room type not found"));
        return roomTypeMapper.mapToRoomTypeDto(roomType);
    }

    //Get all room type
    @Override
    public Page<RoomTypeDto> getAllRoomType(Pageable pageable) {
        Page<RoomType> roomTypes = roomTypeRepository.findAll(pageable);
        return roomTypes.map(roomTypeMapper::mapToRoomTypeDto);
    }

    //Update room type
    @Override
    public RoomTypeDto updateRoom(Integer roomId, RoomTypeDto roomTypeDto) {
        RoomType roomType = roomTypeRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room type not found"));
        roomType.setName(roomType.getName());
        roomType.setDescription(roomType.getDescription());

        RoomType updateRoomtype = roomTypeRepository.save(roomType);
        return roomTypeMapper.mapToRoomTypeDto(updateRoomtype);
    }

    //Delete room type
    @Override
    public void deleteRoomType(Integer id) {
        RoomType roomType = roomTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room type not found"));
        roomTypeRepository.delete(roomType);

    }

    @Override
    public List<RoomTypeDto> getAllRoomType() {
        List<RoomType> roomType = roomTypeRepository.findAll();
        return roomType.stream().map(roomTypeMapper::mapToRoomTypeDto).toList();
    }

}
