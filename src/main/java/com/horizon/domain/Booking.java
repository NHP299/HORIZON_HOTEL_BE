package com.horizon.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private Date checkIn;
    private Date checkOut;
    private Date bookingDate;
    private Short status;
    private String note;
    private BigInteger totalPrice;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

}
