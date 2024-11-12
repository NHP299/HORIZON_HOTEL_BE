package com.horizon.controller.admin;


import com.horizon.dto.RoomDto;
import com.horizon.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@AllArgsConstructor
@RestController
@RequestMapping("/admin/rooms")
@Validated
public class RoomController {

    private RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomDto> create(
            @RequestBody RoomDto roomDto) {
        RoomDto room = roomService.create(roomDto);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<RoomDto>> getAll(
            Pageable pageable) {
        Page<RoomDto> rooms = roomService.getAll(pageable);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("{id}")
    public ResponseEntity<RoomDto> getById(
            @PathVariable("id") Integer roomId) {
        RoomDto roomDto = roomService.getById(roomId);
        return ResponseEntity.ok(roomDto);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<RoomDto>> search(
            @RequestParam String input,
            Pageable pageable) {
        Page<RoomDto> rooms = roomService.find(input, pageable);
        return ResponseEntity.ok(rooms);
    }

    @PutMapping({"{id}"})
    public ResponseEntity<RoomDto> update(
            @PathVariable("id") Integer roomId,
            @RequestBody  RoomDto updateRoom) {
        RoomDto roomDto = roomService.update(roomId, updateRoom);
        return ResponseEntity.ok(roomDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(
            @PathVariable("id") Integer roomId) {
        roomService.delete(roomId);
        return new ResponseEntity<>("Room deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/by-room-type")
    public ResponseEntity<Page<RoomDto>> getByRoomTypeName(
            @RequestParam String roomTypeName,
            Pageable pageable) {
        Page<RoomDto> rooms = roomService.getByRoomTypeName(roomTypeName, pageable);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/filter-by-status")
    public ResponseEntity<Page<RoomDto>> getByStatus(
            @RequestParam String status,
            Pageable pageable) {
        Page<RoomDto> rooms = roomService.getByStatus(status, pageable);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping
    public ResponseEntity<Page<RoomDto>> getIsAvailable(
            Pageable pageable) {
        Page<RoomDto> rooms = roomService.getIsAvailable(pageable);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/range")
    public ResponseEntity<Page<RoomDto>> getAvailable(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            Pageable pageable) {
        Page<RoomDto> availableRooms = roomService.findAvailable(startDate, endDate, pageable);
        return ResponseEntity.ok(availableRooms);
    }


}
