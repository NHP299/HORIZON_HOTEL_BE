package com.horizon.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "promotion_condition")
public class PromotionCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    @NotNull(message = "Promotion cannot be null")
    private Promotion promotion;

    @ManyToOne
    @JoinColumn(name = "promotion_type_id")
    @NotNull(message = "Promotion Type cannot be null")
    private PromotionType promotionType;

    private Integer value;
    private LocalTime startTime;
    private LocalTime endTime;
}
