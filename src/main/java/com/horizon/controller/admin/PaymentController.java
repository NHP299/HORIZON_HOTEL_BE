package com.horizon.controller.admin;

import com.horizon.dto.PaymentTransactionDto;
import com.horizon.response.ResponseObject;
import com.horizon.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("AdminPaymentController")
@RequestMapping("${spring.application.api-prefix-admin}/payment")
@CrossOrigin
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("update-status")
    public ResponseObject<?> updateStatus(@RequestParam Integer id, @RequestBody PaymentTransactionDto paymentTransactionDto) {
        try {
            PaymentTransactionDto dto = paymentService.update(id, paymentTransactionDto);
            return new ResponseObject<>(HttpStatus.OK, "success", dto);
        } catch (Exception e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @GetMapping("/get-all")
    public ResponseObject<?> getAll(Pageable pageable) {
        try {
            Page<PaymentTransactionDto> paymentTransactions = paymentService.getAll(pageable);
            return new ResponseObject<>(HttpStatus.OK, "success", paymentTransactions);
        } catch (Exception e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", null);
        }
    }
}
