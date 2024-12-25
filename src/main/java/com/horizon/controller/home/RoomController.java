package com.horizon.controller.home;

import com.horizon.dto.RoomDto;
import com.horizon.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-home}/rooms")
@Validated
public class RoomController {

    private RoomService roomService;

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
