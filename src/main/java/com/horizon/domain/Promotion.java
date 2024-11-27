package com.horizon.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    private String description;

    @NotNull(message = "Start time cannot be null")
    @DateTimeFormat(pattern = "HH:mm:ss, dd-MM-yyyy")
    @JsonFormat(pattern = "HH:mm:ss, dd-MM-yyyy")
    private LocalDateTime startTime;

    @NotNull(message = "End time cannot be null")
    @DateTimeFormat(pattern = "HH:mm:ss, dd-MM-yyyy")
    @JsonFormat(pattern = "HH:mm:ss, dd-MM-yyyy")
    private LocalDateTime endTime;

    @NotNull(message = "Maximum usage cannot be null")
    @Positive(message = "Maximum usage must be greater than 0")
    private Integer maxUsage;

    @NotNull(message = "Maximum amount cannot be null")
    @Positive(message = "Maximum amount must be greater than 0")
    private Integer maxAmount;
}
