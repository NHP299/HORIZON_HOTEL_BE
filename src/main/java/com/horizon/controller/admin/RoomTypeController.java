package com.horizon.controller.admin;

import com.horizon.domain.RoomType;
import com.horizon.dto.RoomTypeDto;
import com.horizon.service.RoomTypeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
@RequestMapping("/admin/room-type")
public class RoomTypeController {
    @Autowired
    private RoomTypeService roomTypeService;

    //Add room type
    @PostMapping
    public ResponseEntity<RoomTypeDto> createRoomType(@RequestBody RoomTypeDto roomTypeDto) {
        RoomTypeDto saveRoomType = roomTypeService.crateRoomType(roomTypeDto);
        return new ResponseEntity<>(saveRoomType, HttpStatus.CREATED);
    }

    //Get room type by id
    @GetMapping("/{id}")
    public ResponseEntity<RoomTypeDto> getRoomTypeById(@PathVariable("id") Integer id) {
        RoomTypeDto roomTypeDto = roomTypeService.getRoomTypeById(id);
        return ResponseEntity.ok(roomTypeDto);
    }

    //Get all room type
    @GetMapping
    public ResponseEntity<List<RoomTypeDto>> getAllRoomType() {
        List<RoomTypeDto> roomTypeDtos = roomTypeService.getAllRoomType();
        return ResponseEntity.ok(roomTypeDtos);
    }

    //Update room type
    @PutMapping("/{id}")
    public ResponseEntity<RoomTypeDto> updateRoomType(@PathVariable("id") Integer roomTypeId, @RequestBody RoomTypeDto roomTypeDto) {
        RoomTypeDto updateRoomType = roomTypeService.updateRoom(roomTypeId, roomTypeDto);
        return ResponseEntity.ok(updateRoomType);
    }

    //Delete room type
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoomType(@PathVariable("id") Integer roomTypeId) {
        roomTypeService.deleteRoomType(roomTypeId);
        return new ResponseEntity<>("Room type deleted successfully", HttpStatus.OK);
    }
}
