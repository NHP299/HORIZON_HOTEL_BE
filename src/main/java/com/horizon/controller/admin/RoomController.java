package com.horizon.controller.admin;


import com.horizon.domain.Room;
import com.horizon.dto.RoomDto;
import com.horizon.response.ResponseObject;
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
@RestController("AdminRoomController")
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-admin}/rooms")
@Validated
public class RoomController {

    private RoomService roomService;

    @PostMapping
    public ResponseObject<RoomDto> create(
            @RequestBody RoomDto roomDto) {
        RoomDto room = roomService.create(roomDto);
        return new ResponseObject<>(HttpStatus.OK, "Success", room);
    }

    @GetMapping
    public ResponseObject<?> getAll(Pageable pageable) {
        Page<RoomDto> rooms = roomService.getAll(pageable);
        return new ResponseObject<>(HttpStatus.OK, "Success", rooms);
    }

    @GetMapping("/all")
    public ResponseObject<?> getAllIsActivated(Pageable pageable) {
        Page<RoomDto> rooms = roomService.getAllIsActivated(pageable);
        return new ResponseObject<>(HttpStatus.OK, "Success", rooms);
    }

    @GetMapping("{id}")
    public ResponseObject<RoomDto> getById(
            @PathVariable("id") Integer roomId) {
        RoomDto roomDto = roomService.getById(roomId);
        return new ResponseObject<>(HttpStatus.OK, "Success", roomDto);
    }

    @GetMapping("/search")
    public ResponseObject<List<RoomDto>> search(
            @RequestParam String input) {
        List<RoomDto> rooms = roomService.find(input);
        return new ResponseObject<>(HttpStatus.OK, "Success", rooms);
    }

    @PutMapping({"{id}"})
    public ResponseObject<RoomDto> update(
            @PathVariable("id") Integer roomId,
            @RequestBody  RoomDto updateRoom) {
        RoomDto roomDto = roomService.update(roomId, updateRoom);
        return new ResponseObject<>(HttpStatus.OK, "Success", roomDto);
    }

    @DeleteMapping("{id}")
    public ResponseObject<String> delete(
            @PathVariable("id") Integer roomId) {
        roomService.delete(roomId);
        return new ResponseObject<>(HttpStatus.OK, "Success", "Room deleted successfully");
    }

    @GetMapping("/by-room-type")
    public ResponseObject<?> getByRoomTypeName(
            @RequestParam String roomTypeName,
            Pageable pageable) {
        Page<RoomDto> rooms = roomService.getByRoomTypeName(roomTypeName, pageable);
        return new ResponseObject<>(HttpStatus.OK, "Success", rooms);
    }

    @GetMapping("/filter-by-status")
    public ResponseObject<?> getByStatus(
            @RequestParam Room.Status status,
            Pageable pageable) {
        Page<RoomDto> rooms = roomService.getByStatus(status,pageable);
        return new ResponseObject<>(HttpStatus.OK, "Success", rooms);
    }

    @GetMapping("/is-available")
    public ResponseObject<?> getIsAvailable(Pageable pageable) {
        Page<RoomDto> rooms = roomService.getIsAvailable(pageable);
        return new ResponseObject<>(HttpStatus.OK, "Success", rooms);
    }

    @GetMapping("/range")
    public ResponseObject<?> getAvailable(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            Pageable pageable) {
        Page<RoomDto> availableRooms = roomService.findAvailable(startDate, endDate, pageable);
        return new ResponseObject<>(HttpStatus.OK, "Success", availableRooms);
    }
}
