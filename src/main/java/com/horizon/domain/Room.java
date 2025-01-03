package com.horizon.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @JoinColumn(name = "room_type_id", nullable = false)
    @NotNull(message = "Room type must not be null")
    private RoomType roomType;

    @NotBlank(message = "Room name must not be blank")
    @Size(max = 100, message = "Room name must not exceed 100 characters")
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Discount Type must be null")
    private Room.Status status;

    @Min(value = 1, message = "Floor must be greater than or equal to 1")
    @Max(value = 100, message = "Floor must not exceed 100")
    private Integer floor;

    @NotNull(message = "Price must not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    private Boolean isActivated;

    public enum Status {
        AVAILABLE, OCCUPIED, RESERVED, MAINTENANCE;
    }
}
