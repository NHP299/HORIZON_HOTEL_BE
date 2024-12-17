package com.horizon.controller.wallet;

import com.horizon.domain.Payment;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("${spring.application.api-prefix}/payment")
@CrossOrigin
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final BookingService bookingService;

    @PostMapping("/vn-pay")
    public ResponseObject<PaymentDTO.VNPayResponse> pay(HttpServletRequest request,@RequestBody BookingDto bookingDto) throws UnsupportedEncodingException {
        PaymentDTO.VNPayResponse response = paymentService.createVnPayPayment(request);
        bookingService.create(request, bookingDto, response.paymentUrl);
        return new ResponseObject<>(HttpStatus.OK, "Success", response);
    }

    @GetMapping("/vn-pay-callback")
    public ResponseObject<PaymentDTO.VNPayResponse> payCallbackHandler(HttpServletRequest request,  HttpServletResponse response) throws IOException {
        String status = request.getParameter("vnp_ResponseCode");
        if (status.equals("00")) {
            paymentService.updateSuccessPayment(request);
            bookingService.confirmBooking(request);
            response.sendRedirect("http://localhost:3000/bookingCart");
            return new ResponseObject<>(HttpStatus.OK, "Success", null);
        } else {
            paymentService.updateFailPayment(request);
            response.sendRedirect("http://localhost:3000/payment-fail");
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", null);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<PaymentTransactionDto>> getAll() {
        return ResponseEntity.ok(paymentService.getAll());
    }
}
