package com.horizon.controller.admin;


import com.horizon.dto.RoomDto;
import com.horizon.service.RoomService;
import com.horizon.validation.RoomInputValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@AllArgsConstructor
@RestController
@RequestMapping("/admin/rooms")
@Validated
public class RoomController {

    private RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomDto> createEmployee(
            @RequestBody RoomDto roomDto) {
        RoomDto room = roomService.createRoom(roomDto);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<RoomDto>> getAllRooms(
            Pageable pageable) {
        Page<RoomDto> rooms = roomService.getAllRooms(pageable);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("{id}")
    public ResponseEntity<RoomDto> getRoomById(
            @PathVariable("id") Integer roomId) {
        RoomDto roomDto = roomService.getRoomById(roomId);
        return ResponseEntity.ok(roomDto);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<RoomDto>> searchRooms(
            @RequestParam String input,
            Pageable pageable) {
        Page<RoomDto> rooms = roomService.findRoom(input, pageable);
        return ResponseEntity.ok(rooms);
    }

    @PutMapping({"{id}"})
    public ResponseEntity<RoomDto> updateRoom(
            @PathVariable("id") Integer roomId,
            @RequestBody  RoomDto updateRoom) {
        RoomDto roomDto = roomService.updateRoom(roomId, updateRoom);
        return ResponseEntity.ok(roomDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRoom(
            @PathVariable("id") Integer roomId) {
        roomService.deleteRoom(roomId);
        return new ResponseEntity<>("Room deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/by-room-type")
    public ResponseEntity<Page<RoomDto>> getRoomsByRoomTypeName(
            @RequestParam String roomTypeName,
            Pageable pageable) {
        Page<RoomDto> rooms = roomService.getRoomsByRoomTypeName(roomTypeName, pageable);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/filter-by-status")
    public ResponseEntity<Page<RoomDto>> getRoomsByStatus(
            @RequestParam String status,
            Pageable pageable) {
        Page<RoomDto> rooms = roomService.getRoomsByStatus(status, pageable);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping
    public ResponseEntity<Page<RoomDto>> getRoomsIsAvailable(
            Pageable pageable) {
        Page<RoomDto> rooms = roomService.getRoomsIsAvailable(pageable);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/range")
    public ResponseEntity<Page<RoomDto>> getAvailableRooms(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            Pageable pageable) {
        Page<RoomDto> availableRooms = roomService.findAvailableRooms(startDate, endDate, pageable);
        return ResponseEntity.ok(availableRooms);
    }


}
