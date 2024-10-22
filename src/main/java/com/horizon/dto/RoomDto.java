package com.horizon.dto;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private Integer id;
    private Integer roomTypeId;
    @NotNull(message = "Room name must not be null")
    @Size(min = 1, max = 255, message = "Room name must be between 1 and 255 characters")
    private String name;
    @Nullable
    private String status;
    @NotNull(message = "Floor must not be null")
    private Integer floor;
    @NotNull(message = "Price must not be null")
    private Double price;
    private String description;
    @Nullable
    private Boolean isActivated;
}
