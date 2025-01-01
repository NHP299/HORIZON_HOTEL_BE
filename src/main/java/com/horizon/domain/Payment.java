package com.horizon.domain;


import jakarta.persistence.*;
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

    private String transactionId;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private Payment.Status status;
    private Timestamp paymentTime;
    private Timestamp createdTime;
    private Timestamp expiredTime;
    private String description;
    private String paymentMethod;
    private String bankCode;
    private String cardType;
    private String extraData;

    public enum Status {
        PENDING, SUCCESS, FAILED;
    }
}
