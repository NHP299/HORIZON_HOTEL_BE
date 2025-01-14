package com.horizon.controller.home;

import com.horizon.dto.BookingDto;
import com.horizon.response.ResponseObject;
import com.horizon.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController("HomeBookingController")
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-home}/bookings")
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("/get-by-account-id/{accountId}")
    private ResponseObject<?> getByAccountId(@PathVariable Integer accountId, Pageable pageable) {
        Page<Map<String, Object>> bookings = bookingService.getByAccountId(accountId, pageable);
        return new ResponseObject<>(HttpStatus.OK, "Success", bookings);
    }

    @PostMapping("/cancel")
    private ResponseObject<?> cancelBooking(@RequestParam Integer accountId, @RequestParam Integer bookingId) {
        bookingService.cancelBooking(accountId, bookingId);
        return new ResponseObject<>(HttpStatus.OK, "Success", null);
    }
}
