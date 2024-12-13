package com.horizon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private Integer id;
    private Integer accountId;
    private Integer paymentId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private LocalDate bookingDate;
    private Integer adult;
    private Integer child;
    private Integer baby;
    private String status;
    private String note;
    private Double totalPrice;
    private Integer promotionId;
    private List<Integer> roomIds;
}
