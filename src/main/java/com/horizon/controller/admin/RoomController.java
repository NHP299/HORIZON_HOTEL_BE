package com.horizon.controller.admin;


import com.horizon.dto.RoomDto;
import com.horizon.service.RoomService;
import com.horizon.validation.RoomInputValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/admin/rooms")
@Validated
public class RoomController {

    private RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomDto> createEmployee(@RequestBody RoomDto roomDto) {
        return new ResponseEntity<>(roomService.createRoom(roomDto), HttpStatus.CREATED);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<RoomDto>> getAllRooms(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(roomService.getAllRooms(page, size));
    }

    @GetMapping("{id}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable("id") Integer roomId) {
        return ResponseEntity.ok(roomService.getRoomById(roomId));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<RoomDto>> searchRooms(@RequestParam String input, Pageable pageable) {
        return ResponseEntity.ok(roomService.findRoom(input, pageable));
    }

    @PutMapping({"{id}"})
    public ResponseEntity<RoomDto> updateRoom(@PathVariable("id") Integer roomId,@RequestBody  RoomDto updateRoom) {
        return ResponseEntity.ok(roomService.updateRoom(roomId, updateRoom));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable("id") Integer roomId) {
        roomService.deleteRoom(roomId);
        return new ResponseEntity<>("Room deleted successfully", HttpStatus.OK);
    }
}
