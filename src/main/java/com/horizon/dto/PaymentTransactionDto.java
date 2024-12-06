package com.horizon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private Integer id;
    private Integer bookingId;
    private String transactionId;
    private Double amount;
    private String status;
    private Timestamp paymentTime;
    private String errorCode;
    private String description;
    private String paymentMethod;
    private Timestamp createdTime;
    private Timestamp updatedTime;
    private String signature;
    private String extraData;
}
