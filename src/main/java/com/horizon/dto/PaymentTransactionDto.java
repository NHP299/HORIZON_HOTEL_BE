package com.horizon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTransactionDto {
    private Integer id;
    private Integer bookingId;
    private String transactionId;
    private Double amount;
    private String status;
    private String paymentTime;
    private String createdTime;
    private String expiredTime;
    private String description;
    private String paymentMethod;
    private String bankCode;
    private String cardType;
    private String extraData;
}
