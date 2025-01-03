package com.horizon.controller.home;

import com.horizon.service.RoomTypeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("HomeRoomTypeController")
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-home}/room-type")
public class RoomTypeController {
    private RoomTypeService roomTypeService;
}
