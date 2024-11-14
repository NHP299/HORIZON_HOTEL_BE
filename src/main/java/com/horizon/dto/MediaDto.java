package com.horizon.dto;

import com.horizon.domain.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MediaDto {
    private Integer id;
    private RoomType roomType;
    private String path;
}
