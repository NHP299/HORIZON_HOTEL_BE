package com.horizon.dto;

import com.horizon.domain.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MediaDto {
    private Integer id;
    @NotNull(message = "Room type cannot be null")
    private RoomType roomType;

    @NotBlank(message = "Path cannot be blank")
    private String path;
}
