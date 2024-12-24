package com.horizon.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomTypeUtilitiesDto {
    private Integer id;
    @NotNull(message = "RoomTypeId cannot be null")
    private Integer roomTypeId;
    @NotNull(message = "UtilityId cannot be null")
    private Integer utilityId;
    private Boolean isActivated;
}
