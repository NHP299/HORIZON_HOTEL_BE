package com.horizon.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomTypeServicesDto {
    private Integer id;
    @NotNull(message = "RoomTypeId cannot be null")
    private Integer roomTypeId;
    @NotNull(message = "ServiceId cannot be null")
    private Integer serviceId;
    private Boolean isActivated;
}
