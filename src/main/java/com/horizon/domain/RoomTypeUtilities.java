package com.horizon.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room_type_utilities")
public class RoomTypeUtilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    @NotNull(message = "RoomTypeId cannot be null")
    private RoomType roomType;

    @ManyToOne
    @JoinColumn(name = "utility_id")
    @NotNull(message = "UtilityId cannot be null")
    private Utilities utility;

    private Boolean isActivated;
}
