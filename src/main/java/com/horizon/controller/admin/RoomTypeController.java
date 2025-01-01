package com.horizon.controller.admin;

import com.horizon.dto.RoomTypeDto;
import com.horizon.response.ResponseObject;
import com.horizon.service.RoomTypeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-admin}/room-type")
public class RoomTypeController {
    @Autowired
    private RoomTypeService roomTypeService;

    //Add room type
    @PostMapping
    public ResponseEntity<RoomTypeDto> create(@RequestBody RoomTypeDto roomTypeDto) {
        RoomTypeDto saveRoomType = roomTypeService.create(roomTypeDto);
        return new ResponseEntity<>(saveRoomType, HttpStatus.CREATED);
    }

    //Get room type by id
    @GetMapping("/{id}")
    public ResponseEntity<RoomTypeDto> getById(@PathVariable("id") Integer id) {
        RoomTypeDto roomTypeDto = roomTypeService.getById(id);
        return ResponseEntity.ok(roomTypeDto);
    }

    //Get all room type
    @GetMapping
    public ResponseEntity<List<RoomTypeDto>> getAll() {
        List<RoomTypeDto> roomTypeDtos = roomTypeService.getAll();
        return ResponseEntity.ok(roomTypeDtos);
    }

    //Update room type
    @PutMapping("/{id}")
    public ResponseEntity<RoomTypeDto> update(@PathVariable("id") Integer roomTypeId, @RequestBody RoomTypeDto roomTypeDto) {
        RoomTypeDto updateRoomType = roomTypeService.update(roomTypeId, roomTypeDto);
        return ResponseEntity.ok(updateRoomType);
    }

    //Delete room type
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer roomTypeId) {
        roomTypeService.delete(roomTypeId);
        return new ResponseEntity<>("Room type deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/allDetail")
    public ResponseObject<?> getAllDetail() {
        try {
            return new ResponseObject<>(HttpStatus.OK,"Success", roomTypeService.getAllDetail());
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }
}
