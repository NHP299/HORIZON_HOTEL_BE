package com.horizon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicesDto {
    private Integer id;
    private Integer roomTypeId;
    private String description;
    private LocalDateTime startedTime;
    private LocalDateTime endTime;
}
