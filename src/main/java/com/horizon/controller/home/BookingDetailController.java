package com.horizon.controller.home;

import com.horizon.dto.BookingDetailDto;
import com.horizon.response.ResponseObject;
import com.horizon.service.BookingDetailService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController("HomeBookingDetailController")
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-home}/booking-details")
public class BookingDetailController {
    private BookingDetailService bookingDetailService;

    @GetMapping("/by-booking-id/{bookingId}")
    public ResponseObject<?> getAllByBookingId(@PathVariable Integer bookingId, Pageable pageable) {
        try {
            Page<BookingDetailDto> bookingDetails = bookingDetailService.getAllByBookingId(bookingId, pageable);
            return new ResponseObject<>(HttpStatus.OK, "success", bookingDetails);
        } catch (Exception e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }
}
