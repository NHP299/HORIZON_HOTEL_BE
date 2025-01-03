package com.horizon.controller.admin;

import com.horizon.dto.BookingDetailDto;
import com.horizon.response.ResponseObject;
import com.horizon.service.BookingDetailService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    public ResponseObject<?> getAll(Pageable pageable) {
        return new ResponseObject<>(HttpStatus.OK, "Success", bookingDetailService.getAll(pageable));
    }

    @PostMapping("/create")
    public ResponseObject<?> create(@RequestBody BookingDetailDto bookingDetailDto) {
        BookingDetailDto bookingDetail = bookingDetailService.create(bookingDetailDto);
        return new ResponseObject<>(HttpStatus.OK, "Success", bookingDetail);
    }


}
