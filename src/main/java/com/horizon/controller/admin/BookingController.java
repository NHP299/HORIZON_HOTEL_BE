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
import java.util.Map;

@AllArgsConstructor
@RestController("AdminBookingController")
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-admin}/bookings")
public class BookingController {
    private BookingService bookingService;

    @GetMapping("/all")
    private ResponseObject<?> getAll(Pageable pageable) {
        Page<Map<String,Object>> bookings = bookingService.getAllBookings(pageable);
        return new ResponseObject<>(HttpStatus.OK, "Success", bookings);
    }

    @PostMapping("/update-status")
    private ResponseObject<?> update(@RequestParam Integer id, @RequestBody BookingDto bookingDto) {
        return new ResponseObject<>
                (HttpStatus.OK, "Success",
                        bookingService.update(id, bookingDto));
    }

}
