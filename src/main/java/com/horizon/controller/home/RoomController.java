package com.horizon.controller.home;

import com.horizon.dto.RoomDto;
import com.horizon.response.ResponseObject;
import com.horizon.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController("HomeRoomController")
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-home}/rooms")
@Validated
public class RoomController {

    private RoomService roomService;

    @GetMapping("/search")
    public ResponseObject<?> searchRooms(
            @RequestParam(required = false) String roomTypeName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
            @RequestParam int adult,
            @RequestParam int child,
            @RequestParam int baby,
            @RequestParam int roomCount,
            Pageable pageable
    ) {
      try {
            Page<RoomDto> rooms = roomService.search(roomTypeName, checkInDate, checkOutDate, adult, child, baby,roomCount, pageable);
            return new ResponseObject<>(HttpStatus.OK, "Success", rooms);
        } catch (Exception e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", null);
      }
    }
}
