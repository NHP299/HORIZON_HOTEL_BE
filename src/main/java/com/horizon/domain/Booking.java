package com.horizon.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "Check-in date must not be null")
    @FutureOrPresent(message = "Check-in date must be today or in the future")
    private LocalDate checkIn;

    @NotNull(message = "Check-out date must not be null")
    @Future(message = "Check-out date must be in the future")
    private LocalDate checkOut;

    @NotNull(message = "Booking date must not be null")
    @PastOrPresent(message = "Booking date must be today or in the past")
    private LocalDate bookingDate;

    @NotNull(message = "Number of adults must not be null")
    @Min(value = 1, message = "There must be at least 1 adult")
    @Max(value = 10, message = "Number of adults must not exceed 10")
    private Integer adult;

    @NotNull(message = "Number of children must not be null")
    @Min(value = 0, message = "Number of children must be at least 0")
    @Max(value = 10, message = "Number of children must not exceed 10")
    private Integer child;

    @NotNull(message = "Number of babies must not be null")
    @Min(value = 0, message = "Number of babies must be at least 0")
    @Max(value = 5, message = "Number of babies must not exceed 5")
    private Integer baby;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Booking status must not be null")
    private Booking.Status status;

    @Size(max = 500, message = "Note must not exceed 500 characters")
    private String note;

    @NotNull(message = "Total price must not be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Total price must be 0 or more")
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    public enum Status {
        PENDING, CONFIRMED, CANCELLED, COMPLETED;
    }
}
