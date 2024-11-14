package com.horizon.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionConditionDto {
    private Integer id;
    @NotNull(message = "Promotion cannot be null")
    private Integer promotionId;
    @NotNull(message = "Promotion Type cannot be null")
    private Integer promotionTypeId;
    private Integer value;
    private LocalTime startTime;
    private LocalTime endTime;
}
