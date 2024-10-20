package com.horizon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicesDto {
    private Integer id;
    private Integer roomTypeId;
    private String description;
    private Timestamp startedTime;
    private Timestamp endTime;
}
