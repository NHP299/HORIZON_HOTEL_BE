package com.horizon.controller.home;

import com.horizon.dto.BookingDto;
import com.horizon.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-home}/bookings")
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("/get-by-account-id/{accountId}")
    private ResponseEntity<List<BookingDto>> getByAccountId(@PathVariable Integer accountId) {
        return new ResponseEntity<>(bookingService.getByAccountId(accountId), HttpStatus.OK);
    }
}
