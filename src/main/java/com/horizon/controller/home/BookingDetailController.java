package com.horizon.controller.home;

import com.horizon.dto.BookingDetailDto;
import com.horizon.service.BookingDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-home}/booking-details")
public class BookingDetailController {
    private BookingDetailService bookingDetailService;

    @GetMapping("/by-booking-id/{bookingId}")
    public ResponseEntity<List<BookingDetailDto>> getAllByBookingId(@PathVariable Integer bookingId) {
        return new ResponseEntity<>(bookingDetailService.getAllByBookingId(bookingId), HttpStatus.OK);
    }
}
