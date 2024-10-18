package com.horizon.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PaymentDto {
    private Integer id;
    private Integer bookingId;
    private Integer transactionId;
    private BigDecimal amount;
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
