package com.horizon.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionTypeDto {
    private Integer id;
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;
    private String description;
}
