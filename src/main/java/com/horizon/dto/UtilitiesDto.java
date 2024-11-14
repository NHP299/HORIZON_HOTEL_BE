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
    private Integer roomTypeId;
    private String name;
    private String description;
}
