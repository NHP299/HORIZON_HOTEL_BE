package com.horizon.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Discount Type must be null")
    private DiscountType discountType;

    @Column(precision = 10, scale = 2)
    @NotNull(message = "Discount Value must be null")
    @DecimalMin(value = "0.01", message = "Discount value must be at least 0.01")
    private BigDecimal discountValue;

    @NotNull(message = "Start date must be null")
    private LocalDate startDate;

    @NotNull(message = "End date must be null")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    @NotNull(message = "RoomType must be null")
    private RoomType roomType;

    private Boolean isActivated;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public enum DiscountType {
        PERCENTAGE, FIXED
    }

}
