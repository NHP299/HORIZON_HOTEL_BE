package com.horizon.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MediaDto {
    private Integer id;
    @NotNull(message = "Room type ID must not be null")
    @Min(value = 1, message = "Room Type ID must be a positive integer")
    private Integer roomTypeId;

    @NotBlank(message = "Path must not be blank")
    private String path;
    private String publicId;

}
