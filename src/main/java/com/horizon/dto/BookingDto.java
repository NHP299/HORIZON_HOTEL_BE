package com.horizon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private Integer id;
    private Integer accountId;
    private Date checkIn;
    private Date checkOut;
    private Date bookingDate;
    private Integer adult;
    private Integer child;
    private Integer baby;
    private String status;
    private String note;
    private Double totalPrice;
    private Integer promotionId;
}
