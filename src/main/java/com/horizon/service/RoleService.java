package com.horizon.service;


import com.horizon.dto.RoleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {
    RoleDto createRole(RoleDto roleDto);
    RoleDto updateRole(RoleDto roleDto, Integer id  );
    RoleDto getRoleById(Integer id);
    void deleteRoleById(Integer id);
    Page<RoleDto> getAllRoles(Pageable pageable);
    List<RoleDto> getAllRoles();
}
