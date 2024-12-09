package com.horizon.controller.admin;

import com.horizon.dto.BookingDto;
import com.horizon.dto.RoomDto;
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
@RestController
@RequestMapping("/admin/bookings")
public class BookingController {

    private BookingService bookingService;

    @GetMapping("/all")
    private ResponseEntity<List<BookingDto>> getAll() {
        return new ResponseEntity<>(bookingService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-by-account-id/{accountId}")
    private ResponseEntity<List<BookingDto>> getByAccountId(@PathVariable Integer accountId) {
        return new ResponseEntity<>(bookingService.getByAccountId(accountId), HttpStatus.OK);
    }


}
