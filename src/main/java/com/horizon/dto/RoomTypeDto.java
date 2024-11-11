package com.horizon.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RoomTypeDto {
    private Integer id;
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    private String description;
}
