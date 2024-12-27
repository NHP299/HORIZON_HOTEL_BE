package com.horizon.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Discount Type must be null")
    private Room.Status status;
    private Integer floor;
    private Double price;
    private String description;
    private Boolean isActivated;

    public enum Status {
        AVAILABLE, OCCUPIED, RESERVED, MAINTENANCE;
    }
}
