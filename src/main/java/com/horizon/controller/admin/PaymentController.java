package com.horizon.controller.admin;

import com.horizon.dto.PaymentTransactionDto;
import com.horizon.response.ResponseObject;
import com.horizon.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("AdminPaymentController")
@RequestMapping("${spring.application.api-prefix-admin}/payment")
@CrossOrigin
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/get-all")
    public ResponseObject<List<PaymentTransactionDto>> getAll() {
        try {
            List<PaymentTransactionDto> paymentTransactions = paymentService.getAll();
            return new ResponseObject<>(HttpStatus.OK, "success", paymentTransactions);
        } catch (Exception e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", null);
        }
    }
}
