package com.horizon.service.impl;

import com.horizon.domain.*;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.repository.RoomTypeRepository;
import com.horizon.repository.RoomTypeUtilitiesRepository;
import com.horizon.repository.UtilitiesRepository;
import com.horizon.service.RoomTypeUtilitiesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomTypeUtilitiesServiceImpl implements RoomTypeUtilitiesService {

    private RoomTypeUtilitiesRepository rtUtilitiesRepository;
    private RoomTypeRepository roomTypeRepository;
    private UtilitiesRepository utilitiesRepository;


    @Override
    public void updateUtilitiesForRoomType(Integer roomTypeId, String listUtilities) {
        RoomType roomType = roomTypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("RoomType not found " + roomTypeId));

        if (listUtilities == null || listUtilities.trim().isEmpty()) {
            rtUtilitiesRepository.deactivateAll(roomTypeId);
        } else {
            Set<Integer> inputUtilityIds = parseUtilityIds(listUtilities);
            inputUtilityIds.forEach(UtilityId -> updateOrCreate(roomType, UtilityId));
            rtUtilitiesRepository.deactivate(roomTypeId, inputUtilityIds);
        }
    }

    @Override
    public List<Map<String, Object>> getAll() {
        return rtUtilitiesRepository.findRoomTypeUtilities();
    }

    @Override
    public Map<String, Object> getByRoomTypeId(Integer roomTypeId) {
        return rtUtilitiesRepository.findByRoomTypeId(roomTypeId);
    }


    private Set<Integer> parseUtilityIds(String listUtilities) {
        return Arrays.stream(listUtilities.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    private void updateOrCreate(RoomType roomType, Integer UtilityId) {
        Optional<RoomTypeUtilities> existingUtility = rtUtilitiesRepository.findByRoomTypeIdAndUtilityId(
                roomType.getId(), UtilityId);

        if (existingUtility.isPresent()) {
            RoomTypeUtilities roomTypeUtilities = existingUtility.get();
            roomTypeUtilities.setIsActivated(true);
            rtUtilitiesRepository.save(roomTypeUtilities);
        } else {
            Utilities utilities = utilitiesRepository.findById(UtilityId)
                    .orElseThrow(() -> new ResourceNotFoundException("Utilities not found " + UtilityId));
            RoomTypeUtilities roomTypeUtilities= new RoomTypeUtilities();
            roomTypeUtilities.setRoomType(roomType);
            roomTypeUtilities.setUtility(utilities);
            roomTypeUtilities.setIsActivated(true);
            rtUtilitiesRepository.save(roomTypeUtilities);
        }
    }
}
