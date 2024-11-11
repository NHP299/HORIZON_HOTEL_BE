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
    @NotNull(message = "Room type id cannot be null")
    private Integer roomTypeId;

    private String description;

    @NotNull(message = "Start time cannot be null")
    @DateTimeFormat(pattern = "HH:mm:ss, dd-MM-yyyy")
    @JsonFormat(pattern = "HH:mm:ss, dd-MM-yyyy")
    private LocalDateTime startedTime;

    @NotNull(message = "End time cannot be null")
    @DateTimeFormat(pattern = "HH:mm:ss, dd-MM-yyyy")
    @JsonFormat(pattern = "HH:mm:ss, dd-MM-yyyy")
    private LocalDateTime endTime;
}
