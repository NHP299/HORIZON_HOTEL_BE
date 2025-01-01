package com.horizon.controller.home;

import com.horizon.dto.BookingDto;
import com.horizon.response.ResponseObject;
import com.horizon.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController("HomeBookingController")
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-home}/bookings")
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("/get-by-account-id/{accountId}")
    private ResponseObject<?> getByAccountId(@PathVariable Integer accountId) {
        try {
            List<BookingDto> bookings = bookingService.getByAccountId(accountId);
            return new ResponseObject<>(HttpStatus.OK,"success",bookings);
        } catch (Exception e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST,"Failed",e.getMessage());
        }
    }
}
