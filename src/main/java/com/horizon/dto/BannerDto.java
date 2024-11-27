package com.horizon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BannerDto {
    private Integer id;
    private String name;
    private String description;
    private String path;
    private Integer bannerTypeId;
}
