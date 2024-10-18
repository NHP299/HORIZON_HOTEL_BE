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

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private String transactionId;
    private Integer amount;
    private Short status;
    private Timestamp paymentTime;
    private String errorCode;
    private String description;
    private String paymentMethod;
    private Timestamp createdTime;
    private String signature;
    private String extraData;

}
