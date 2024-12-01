package com.horizon.controller.admin;

import com.horizon.service.RoomService;
import com.horizon.service.RoomTypeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/home")
public class HomePageController {
    private RoomTypeService roomTypeService;
    private RoomService roomService;

    @GetMapping("/roomtypeAndMedia")
    public List<Map<String, Object>> getRoomTypeMedia() {
        return roomTypeService.getRoomTypeMedia();
    }

    @GetMapping("/roomDetail")
    public List<Map<String, Object>> getRoomDetail() {
        return roomService.getRoomDetail();
    }
}
