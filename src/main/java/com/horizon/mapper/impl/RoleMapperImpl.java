package com.horizon.mapper.impl;

import com.horizon.domain.Role;
import com.horizon.dto.RoleDto;
import com.horizon.mapper.RoleMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleMapperImpl implements RoleMapper {
    @Override
    public RoleDto toRoleDto(Role role) {
        return new RoleDto(role.getId(), role.getRoleName());
    }

    @Override
    public Role toRole(RoleDto roleDto) {
        return new Role(roleDto.getId(), roleDto.getRoleName());
    }
}
