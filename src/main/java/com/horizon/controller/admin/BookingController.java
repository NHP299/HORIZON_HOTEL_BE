package com.horizon.controller.admin;

import com.horizon.dto.BookingDto;
import com.horizon.dto.RoomDto;
import com.horizon.response.ResponseObject;
import com.horizon.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController("AdminBookingController")
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-admin}/bookings")
public class BookingController {
    private BookingService bookingService;

    @GetMapping("/all")
    private ResponseObject<?> getAll(Pageable pageable) {
        return new ResponseObject<>
                (HttpStatus.OK, "Success",
                        bookingService.getAll(pageable));
    }

    @PostMapping("/update")
    private ResponseObject<?> update(@RequestParam Integer id, @RequestBody BookingDto bookingDto) {
        return new ResponseObject<>
                (HttpStatus.OK, "Success",
                        bookingService.update(id, bookingDto));
    }

}
