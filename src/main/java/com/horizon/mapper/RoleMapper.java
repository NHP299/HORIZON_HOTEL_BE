package com.horizon.mapper;

import com.horizon.domain.Role;
import com.horizon.dto.RoleDto;

public interface RoleMapper {
    RoleDto toRoleDto(Role role);
    Role toRole(RoleDto roleDto);

}
