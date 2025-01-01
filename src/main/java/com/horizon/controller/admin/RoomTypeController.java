package com.horizon.controller.admin;

import com.horizon.dto.RoomTypeDto;
import com.horizon.service.RoomTypeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("AdminRoomTypeController")
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-admin}/room-type")
public class RoomTypeController {
    private RoomTypeService roomTypeService;

    @PostMapping
    public ResponseEntity<RoomTypeDto> createRoomType(@RequestBody RoomTypeDto roomTypeDto) {
        RoomTypeDto saveRoomType = roomTypeService.createRoomType(roomTypeDto);
        return new ResponseEntity<>(saveRoomType, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomTypeDto> getRoomTypeById(@PathVariable("id") Integer id) {
        RoomTypeDto roomTypeDto = roomTypeService.getRoomTypeById(id);
        return ResponseEntity.ok(roomTypeDto);
    }

    @GetMapping
    public ResponseEntity<List<RoomTypeDto>> getAllRoomType() {
        List<RoomTypeDto> roomTypeDtos = roomTypeService.getAllRoomType();
        return ResponseEntity.ok(roomTypeDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomTypeDto> updateRoomType(@PathVariable("id") Integer roomTypeId, @RequestBody RoomTypeDto roomTypeDto) {
        RoomTypeDto updateRoomType = roomTypeService.updateRoom(roomTypeId, roomTypeDto);
        return ResponseEntity.ok(updateRoomType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoomType(@PathVariable("id") Integer roomTypeId) {
        roomTypeService.deleteRoomType(roomTypeId);
        return new ResponseEntity<>("Room type deleted successfully", HttpStatus.OK);
    }
}
