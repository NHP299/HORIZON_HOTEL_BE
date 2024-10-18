package com.horizon.dto;

import java.math.BigDecimal;
import java.util.Date;

public class BookingDto {
    private Integer id;
    private Integer accountId;
    private Date checkIn;
    private Date checkOut;
    private Date bookingDate;
    private String status;
    private String note;
    private BigDecimal totalPrice;
    private Integer promotionId;
}
