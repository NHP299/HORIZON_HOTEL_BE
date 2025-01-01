package com.horizon.service.impl;

import com.horizon.domain.RoomTypeServices;
import com.horizon.domain.RoomType;
import com.horizon.domain.Services;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.repository.RoomTypeServicesRepository;
import com.horizon.repository.RoomTypeRepository;
import com.horizon.repository.ServicesRepository;
import com.horizon.service.RoomTypeServicesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoomTypeServicesServiceImpl implements RoomTypeServicesService {

    private RoomTypeServicesRepository rtServicesRepository;
    private RoomTypeRepository roomTypeRepository;
    private ServicesRepository servicesRepository;

    @Override
    public void updateServicesForRoomType(Integer roomTypeId, String listServices) {
        RoomType roomType = roomTypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("RoomType not found " + roomTypeId));

        if (listServices == null || listServices.trim().isEmpty()) {
            rtServicesRepository.deactivateAll(roomTypeId);
        } else {
            Set<Integer> inputServiceIds = parseServiceIds(listServices);
            inputServiceIds.forEach(serviceId -> updateOrCreate(roomType, serviceId));
            rtServicesRepository.deactivate(roomTypeId, inputServiceIds);
        }
    }

    private Set<Integer> parseServiceIds(String listServices) {
        return Arrays.stream(listServices.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    private void updateOrCreate(RoomType roomType, Integer serviceId) {
        Optional<RoomTypeServices> existingService = rtServicesRepository.findByRoomTypeIdAndServiceId(
                roomType.getId(), serviceId);

        if (existingService.isPresent()) {
            RoomTypeServices roomTypeService = existingService.get();
            roomTypeService.setIsActivated(true);
            rtServicesRepository.save(roomTypeService);
        } else {
            Services service = servicesRepository.findById(serviceId)
                    .orElseThrow(() -> new ResourceNotFoundException("Service not found " + serviceId));
            RoomTypeServices roomTypeService = new RoomTypeServices();
            roomTypeService.setRoomType(roomType);
            roomTypeService.setService(service);
            roomTypeService.setIsActivated(true);
            rtServicesRepository.save(roomTypeService);
        }
    }

    @Override
    public List<Map<String, Object>> getAll() {
        return rtServicesRepository.findRoomTypeService();
    }

    @Override
    public Map<String, Object> getByRoomTypeId(Integer roomTypeId) {
        return rtServicesRepository.findByRoomTypeId(roomTypeId);
    }

}
