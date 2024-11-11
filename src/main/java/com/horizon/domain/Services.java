package com.horizon.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "services")
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    @NotNull(message = "Room type cannot be null")
    private RoomType roomType;

    private String description;

    @NotNull(message = "Start time cannot be null")
    @DateTimeFormat(pattern = "HH:mm:ss, dd-MM-yyyy")
    @JsonFormat(pattern = "HH:mm:ss, dd-MM-yyyy")
    private LocalDateTime startedTime;

    @NotNull(message = "End time cannot be null")
    @DateTimeFormat(pattern = "HH:mm:ss, dd-MM-yyyy")
    @JsonFormat(pattern = "HH:mm:ss, dd-MM-yyyy")
    private LocalDateTime endTime;
}
