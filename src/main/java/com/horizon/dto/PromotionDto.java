package com.horizon.dto;

import java.sql.Timestamp;

public class PromotionDto {
    private Integer id;
    private String name;
    private String description;
    private Integer discountCondition;
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer maxUsage;
    private String voucherType;
    private Integer maxAmount;
}
