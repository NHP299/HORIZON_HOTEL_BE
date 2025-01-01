package com.horizon.service;

import java.util.List;
import java.util.Map;

public interface RoomTypeServicesService {
    void updateServicesForRoomType(Integer roomTypeId, String listServices);
    List<Map<String, Object>> getAll();
    Map<String, Object> getByRoomTypeId(Integer roomTypeId);
}
