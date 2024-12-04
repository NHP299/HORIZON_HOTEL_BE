package com.horizon.controller.admin;

import com.horizon.dto.RoomDto;
import com.horizon.service.RoomService;
import com.horizon.service.RoomTypeService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
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

    @GetMapping("/search")
    public ResponseEntity<List<RoomDto>> searchRooms(
            @RequestParam(required = false) String roomTypeName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
            @RequestParam int guestCount,
            @RequestParam int roomCount
    ) {
        List<RoomDto> rooms = roomService.search(roomTypeName, checkInDate, checkOutDate, guestCount, roomCount);
        return ResponseEntity.ok(rooms);
    }

}
