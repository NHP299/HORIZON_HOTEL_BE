package com.horizon.dto;

import com.horizon.domain.Promotion;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDto {

    private Integer id;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;
    @NotNull(message = "Discount Type must be null")
    private Promotion.DiscountType discountType;

    @DecimalMin(value = "0.01", message = "Discount value must be at least 0.01")
    @NotNull(message = "Discount Value must be null")
    private BigDecimal discountValue;

    @NotNull(message = "Start date must be null")
    private LocalDate startDate;

    @NotNull(message = "End date must be null")
    private LocalDate endDate;

    @NotNull(message = "RoomType must be null")
    private Integer roomTypeId;

    private Boolean isActivated;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
