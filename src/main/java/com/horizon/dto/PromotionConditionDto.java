package com.horizon.dto;

import com.horizon.domain.Promotion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionConditionDto {
    private Integer id;
    private Integer promotionId;
    private Integer promotionTypeId;
    private Integer value;
    private Time startTime;
    private Time endTime;
}
