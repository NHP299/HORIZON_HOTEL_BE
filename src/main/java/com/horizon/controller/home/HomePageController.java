package com.horizon.controller.home;

import com.horizon.dto.RoomDto;
import com.horizon.response.ResponseObject;
import com.horizon.service.RoomService;
import com.horizon.service.RoomTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-home}/home")
public class HomePageController {
    private RoomTypeService roomTypeService;
    private RoomService roomService;

    @GetMapping("/roomTypeAndMedia")
    public ResponseObject<?> getRoomTypeMedia(Pageable pageable) {
        try {
            return new ResponseObject<>(HttpStatus.OK, "Success", roomTypeService.getRoomTypeMedia(pageable));
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @GetMapping("/roomDetail")
    public ResponseObject<?> getRoomDetail(Pageable pageable) {
        try {
            return new ResponseObject<>(HttpStatus.OK, "Success", roomService.getRoomDetail(pageable));
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @GetMapping("/roomDetailById/{id}")
    public ResponseObject<?> getRoomDetailById(@PathVariable Integer id) {
        try {
            return new ResponseObject<>(HttpStatus.OK, "Success", roomService.getRoomDetailById(id));
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

}
