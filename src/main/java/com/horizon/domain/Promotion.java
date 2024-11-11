package com.horizon.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

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

    @NotBlank(message = "Discount condition cannot be blank")
    private String discountCondition;

    @NotNull(message = "Discount cannot be null")
    private Double discount;

    @NotNull(message = "Start time cannot be null")
    @DateTimeFormat(pattern = "HH:mm:ss, dd-MM-yyyy")
    @JsonFormat(pattern = "HH:mm:ss, dd-MM-yyyy")
    private LocalDateTime startTime;

    @NotNull(message = "End time cannot be null")
    @DateTimeFormat(pattern = "HH:mm:ss, dd-MM-yyyy")
    @JsonFormat(pattern = "HH:mm:ss, dd-MM-yyyy")
    private LocalDateTime endTime;

    @NotNull(message = "Maximum usage cannot be null")
    @Min(value = 1, message = "Maximum usage must be at least 1")
    private Integer maxUsage;

    @NotBlank(message = "Voucher type cannot be blank")
    @Size(max = 50, message = "Voucher type must be less than 50 characters")
    private String voucherType;

    @NotNull(message = "Maximum amount cannot be null")
    @Min(value = 0, message = "Maximum amount must be at least 0")
    private Integer maxAmount;

}
