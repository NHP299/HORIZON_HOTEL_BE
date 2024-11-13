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
import java.util.List;


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
    public ResponseEntity<List<RoomDto>> getAll() {
        List<RoomDto> rooms = roomService.getAllIsActivated();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("{id}")
    public ResponseEntity<RoomDto> getById(
            @PathVariable("id") Integer roomId) {
        RoomDto roomDto = roomService.getById(roomId);
        return ResponseEntity.ok(roomDto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<RoomDto>> search(
            @RequestParam String input) {
        List<RoomDto> rooms = roomService.find(input);
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
    public ResponseEntity<List<RoomDto>> getByRoomTypeName(
            @RequestParam String roomTypeName) {
        List<RoomDto> rooms = roomService.getByRoomTypeName(roomTypeName);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/filter-by-status")
    public ResponseEntity<List<RoomDto>> getByStatus(
            @RequestParam String status) {
        List<RoomDto> rooms = roomService.getByStatus(status);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getIsAvailable() {
        List<RoomDto> rooms = roomService.getIsAvailable();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/range")
    public ResponseEntity<List<RoomDto>> getAvailable(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<RoomDto> availableRooms = roomService.findAvailable(startDate, endDate);
        return ResponseEntity.ok(availableRooms);
    }


}
