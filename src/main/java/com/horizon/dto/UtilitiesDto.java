package com.horizon.dto;

import com.horizon.domain.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilitiesDto {
    private Integer id;
    @NotNull(message = "Room type cannot be null")
    private Integer roomTypeId;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    private String description;
}
