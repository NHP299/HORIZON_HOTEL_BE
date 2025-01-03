package com.horizon.dto;


import com.horizon.domain.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private Integer id;
    private Integer roomTypeId;
    private String name;
    private Room.Status status;
    private Integer floor;
    private Double price;
    private String description;
    private Boolean isActivated;
}
