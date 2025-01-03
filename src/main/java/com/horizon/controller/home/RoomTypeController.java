package com.horizon.controller.home;

import com.horizon.response.ResponseObject;
import com.horizon.service.RoomTypeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("HomeRoomTypeController")
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-home}/room-type")
public class RoomTypeController {
    @Autowired
    private RoomTypeService roomTypeService;

    @GetMapping("/roomTypeAndMedia")
    public ResponseObject<?> getRoomTypeMedia(Pageable pageable) {
        try {
            return new ResponseObject<>(HttpStatus.OK, "Success", roomTypeService.getRoomTypeMedia(pageable));
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }
}
