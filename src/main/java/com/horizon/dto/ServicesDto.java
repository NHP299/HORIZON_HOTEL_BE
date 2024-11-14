package com.horizon.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.horizon.domain.RoomType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
