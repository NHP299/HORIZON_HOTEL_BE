package com.horizon.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    private LocalDate checkIn;
    private LocalDate checkOut;
    private LocalDate bookingDate;
    private Integer adult;
    private Integer child;
    private Integer baby;
    @Enumerated(EnumType.STRING)
    private Booking.Status status;
    private String note;
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    public enum Status {
        PENDING, CONFIRMED, CANCELLED, COMPLETED;
    }
}
