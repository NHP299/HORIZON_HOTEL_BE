package com.horizon.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @NotBlank(message = "Transaction ID must not be blank")
    private String transactionId;

    @NotNull(message = "Amount must not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    private Double amount;

    @Enumerated(EnumType.STRING)
    private Payment.Status status;

    private Timestamp paymentTime;

    private Timestamp createdTime;

    private Timestamp expiredTime;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @Size(max = 100, message = "Payment method must not exceed 100 characters")
    private String paymentMethod;

    @Size(max = 50, message = "Bank code must not exceed 50 characters")
    private String bankCode;

    @Size(max = 50, message = "Card type must not exceed 50 characters")
    private String cardType;

    @Size(max = 500, message = "Extra data must not exceed 500 characters")
    private String extraData;

    public enum Status {
        PENDING, SUCCESS, FAILED;
    }
}
