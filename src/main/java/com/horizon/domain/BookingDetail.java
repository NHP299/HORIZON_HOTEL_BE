package com.horizon.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking_detail")
public class BookingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    @NotNull(message = "Room must not be null")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    @NotNull(message = "Booking must not be null")
    private Booking booking;

    @NotNull(message = "Price at booking must not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price at booking must be greater than 0")
    private Double priceAtBooking;
}
