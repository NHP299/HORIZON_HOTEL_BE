package com.horizon.controller.admin;

import com.horizon.dto.RoomTypeDto;
import com.horizon.response.ResponseObject;
import com.horizon.service.RoomTypeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController("AdminRoomTypeController")
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-admin}/room-type")
public class RoomTypeController {
    @Autowired
    private RoomTypeService roomTypeService;

    @PostMapping
    public ResponseObject<?> create(@RequestBody RoomTypeDto roomTypeDto) {
        RoomTypeDto saveRoomType = roomTypeService.create(roomTypeDto);
        return new ResponseObject<>(HttpStatus.OK, "Success", saveRoomType);
    }

    @GetMapping("/{id}")
    public ResponseObject<?> getById(@PathVariable("id") Integer id) {
        RoomTypeDto roomTypeDto = roomTypeService.getById(id);
        return new ResponseObject<>(HttpStatus.OK, "Success", roomTypeDto);
    }

    @GetMapping
    public ResponseObject<?> getAll(Pageable pageable) {
        Page<RoomTypeDto> roomTypeDto = roomTypeService.getAll(pageable);
        return new ResponseObject<>(HttpStatus.OK, "Success", roomTypeDto);
    }

    @PutMapping("/{id}")
    public ResponseObject<?> update(@PathVariable("id") Integer roomTypeId, @RequestBody RoomTypeDto roomTypeDto) {
        RoomTypeDto updateRoomType = roomTypeService.update(roomTypeId, roomTypeDto);
        return new ResponseObject<>(HttpStatus.OK, "Success", updateRoomType);
    }

    @DeleteMapping("/{id}")
    public ResponseObject<?> delete(@PathVariable("id") Integer roomTypeId) {
        roomTypeService.delete(roomTypeId);
        return new ResponseObject<>(HttpStatus.OK, "Success", null);
    }

    @GetMapping("/allDetail")
    public ResponseObject<?> getAllDetail(Pageable pageable) {
        try {
            return new ResponseObject<>(HttpStatus.OK,"Success", roomTypeService.getAllDetail(pageable));
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @GetMapping("/DetailBy/{id}")
    public ResponseObject<?> getDetailById(@PathVariable("id") Integer roomTypeId, Pageable pageable) {
        try {
            return new ResponseObject<>(HttpStatus.OK,"Success", roomTypeService.getDetailById(roomTypeId,pageable));
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }
}
