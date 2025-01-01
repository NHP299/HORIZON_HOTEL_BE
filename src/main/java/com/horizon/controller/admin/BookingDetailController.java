package com.horizon.controller.admin;

import com.horizon.dto.BookingDetailDto;
import com.horizon.service.BookingDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController("AdminBookingDetailController")
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-admin}/booking-details")
public class BookingDetailController {
    private BookingDetailService bookingDetailService;

    @GetMapping("/all")
    public ResponseEntity<List<BookingDetailDto>> getAll() {
        return new ResponseEntity<>(bookingDetailService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<BookingDetailDto> create(@RequestBody BookingDetailDto bookingDetailDto) {
        BookingDetailDto bookingDetail = bookingDetailService.create(bookingDetailDto);
        return new ResponseEntity<>(bookingDetail, HttpStatus.CREATED);
    }


}
