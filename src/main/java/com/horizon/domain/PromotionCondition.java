package com.horizon.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

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
    private Promotion promotion;

    @ManyToOne
    @JoinColumn(name = "promotion_type_id")
    private PromotionType promotionType;

    private Integer value;
    private Time startTime;
    private Time endTime;
}
