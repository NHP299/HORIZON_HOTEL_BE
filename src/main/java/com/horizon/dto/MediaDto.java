package com.horizon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MediaDto {
    private Integer id;
    private RoomType roomType;
    private String path;
    private String publicId;
}
