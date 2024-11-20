package com.horizon.dto;

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
