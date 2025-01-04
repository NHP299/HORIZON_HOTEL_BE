package com.horizon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomTypeDto {
    private Integer id;
    private String name;
    private Integer adultCapacity;
    private Integer childCapacity;
    private Integer babyCapacity;
    private String description;
    private Boolean isActivated;
}
