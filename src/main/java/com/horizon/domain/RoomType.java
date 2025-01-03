package com.horizon.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room_type")
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Room type name must not be blank")
    @Size(max = 100, message = "Room type name must not exceed 100 characters")
    private String name;

    @NotNull(message = "Adult capacity must not be null")
    @Min(value = 1, message = "Adult capacity must be at least 1")
    @Max(value = 10, message = "Adult capacity must not exceed 10")
    private Integer adultCapacity;

    @NotNull(message = "Child capacity must not be null")
    @Min(value = 0, message = "Child capacity must be at least 0")
    @Max(value = 10, message = "Child capacity must not exceed 10")
    private Integer childCapacity;

    @NotNull(message = "Baby capacity must not be null")
    @Min(value = 0, message = "Baby capacity must be at least 0")
    @Max(value = 5, message = "Baby capacity must not exceed 5")
    private Integer babyCapacity;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;
}
