package com.horizon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDto {
    private Integer id;
    private String name;
    private String description;
    private String discountCondition;
    private Double discount;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer maxUsage;
    private String voucherType;
    private Integer maxAmount;
}
