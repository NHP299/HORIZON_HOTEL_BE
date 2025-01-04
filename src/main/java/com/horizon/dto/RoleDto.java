package com.horizon.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private Integer id;

    @NotBlank(message = "Role Name must not be blank")
    @Size(max = 100, message = "Role Name must be less than 100 characters")
    private String roleName;

}
