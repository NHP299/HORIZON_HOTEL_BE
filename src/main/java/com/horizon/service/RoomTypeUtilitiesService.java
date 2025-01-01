package com.horizon.service;

import java.util.List;
import java.util.Map;

public interface RoomTypeUtilitiesService {
    void updateUtilitiesForRoomType(Integer roomTypeId, String listUtilities);
    List<Map<String, Object>> getAll();
    Map<String, Object> getByRoomTypeId(Integer roomTypeId);
}
