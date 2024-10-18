package com.horizon.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String discountCondition;
    private Double discount;
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer maxUsage;
    private String voucherType;
    private Integer maxAmount;

}
