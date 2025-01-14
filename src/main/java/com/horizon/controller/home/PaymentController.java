package com.horizon.controller.home;

import com.horizon.dto.BookingDto;
import com.horizon.dto.PaymentDTO;
import com.horizon.dto.PaymentTransactionDto;
import com.horizon.response.ResponseObject;
import com.horizon.service.BookingService;
import com.horizon.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController("HomePaymentController")
@RequestMapping("${spring.application.api-prefix-home}/payment")
@CrossOrigin
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final BookingService bookingService;

    @PostMapping("/cash")
    public ResponseObject<?> payCash(@RequestBody BookingDto bookingDto, HttpServletResponse response) throws IOException {
        try {
            BookingDto booking = bookingService.create(null, bookingDto, null);
            return new ResponseObject<>(HttpStatus.OK, "Success", booking);
        } catch (Exception e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @PostMapping("/vn-pay")
    public ResponseObject<?> pay(HttpServletRequest request,@RequestBody BookingDto bookingDto) throws UnsupportedEncodingException {
        try {
            PaymentDTO.VNPayResponse response = paymentService.createVnPayPayment(request, bookingDto.getTotalPrice());
            bookingService.create(request, bookingDto, response.paymentUrl);
            return new ResponseObject<>(HttpStatus.OK, "Success", response);
        }catch (Exception e){
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @GetMapping("/vn-pay-callback")
    public ResponseObject<PaymentDTO.VNPayResponse> payCallbackHandler(HttpServletRequest request,  HttpServletResponse response) throws IOException {
        String status = request.getParameter("vnp_ResponseCode");
        if (status.equals("00")) {
            paymentService.updateSuccessPayment(request);
            bookingService.confirmBooking(request);
            response.sendRedirect("http://localhost:3000/comfirm");
            return new ResponseObject<>(HttpStatus.OK, "Success", null);
        } else {
            paymentService.updateFailPayment(request);
            response.sendRedirect("http://localhost:3000/comfirm/fail");
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", null);
        }
    }

}
