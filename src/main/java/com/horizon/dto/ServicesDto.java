package com.horizon.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicesDto {
    private Integer id;

    @NotBlank(message = "Name must not be blank")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    @NotNull(message = "Start time must not be null")
    private LocalDateTime startedTime;

    @NotNull(message = "End time must not be null")
    private LocalDateTime endTime;

    private Boolean isActivated;
}
