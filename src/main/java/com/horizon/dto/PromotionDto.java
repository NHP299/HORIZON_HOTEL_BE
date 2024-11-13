package com.horizon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDto {
    private Integer id;
    private String name;
    private String description;
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer maxUsage;
    private Integer maxAmount;
}
