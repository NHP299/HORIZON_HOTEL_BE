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
import java.util.Map;

@Service
@AllArgsConstructor
public class RoomTypeServiceImpl implements RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;
    private final RoomTypeMapper roomTypeMapper;

    @Override
    public RoomTypeDto create(RoomTypeDto roomTypeDto) {
        RoomType roomType = roomTypeMapper.mapToRoomType(roomTypeDto);
        roomType.setIsActivated(true);
        RoomType saveRoomType = roomTypeRepository.save(roomType);
        return roomTypeMapper.mapToRoomTypeDto(saveRoomType);
    }

    @Override
    public RoomTypeDto getById(Integer id) {
        RoomType roomType = roomTypeRepository.findById(id).stream().filter(RoomType::getIsActivated).findFirst().orElseThrow(() -> new ResourceNotFoundException("Room type not found"));
        return roomTypeMapper.mapToRoomTypeDto(roomType);
    }

    @Override
    public RoomTypeDto update(Integer roomId, RoomTypeDto roomTypeDto) {
        RoomType roomType = roomTypeRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room type not found"));
        roomType.setName(roomTypeDto.getName());
        roomType.setDescription(roomTypeDto.getDescription());
        roomType.setAdultCapacity(roomTypeDto.getAdultCapacity());
        roomType.setChildCapacity(roomTypeDto.getChildCapacity());
        roomType.setBabyCapacity(roomTypeDto.getBabyCapacity());
        RoomType updateRoomtype = roomTypeRepository.save(roomType);
        return roomTypeMapper.mapToRoomTypeDto(updateRoomtype);
    }

    @Override
    public void delete(Integer id) {
        RoomType roomType = roomTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room type not found"));
        roomType.setIsActivated(false);
        roomTypeRepository.save(roomType);
    }

    @Override
    public Page<RoomTypeDto> getAll(Pageable pageable) {
        Page<RoomType> roomType = roomTypeRepository.findAllByIsActivatedTrue(pageable);
        return roomType.map(roomTypeMapper::mapToRoomTypeDto);
    }

    @Override
    public List<Map<String, Object>> getRoomTypeMedia() {
        return roomTypeRepository.findRoomTypeMedia();
    }

    @Override
    public List<Map<String, Object>> getAllDetail() {
        return roomTypeRepository.findAllRoomTypeService();
    }

}
