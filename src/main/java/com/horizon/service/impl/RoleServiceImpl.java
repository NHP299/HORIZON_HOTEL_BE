package com.horizon.service.impl;

import com.horizon.domain.Role;
import com.horizon.dto.RoleDto;
import com.horizon.exception.ResourceNotFoundException;
import com.horizon.mapper.RoleMapper;
import com.horizon.repository.RoleRepository;
import com.horizon.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Role role = roleMapper.toRole(roleDto);
        Role saveRole = roleRepository.save(role);
        return roleMapper.toRoleDto(saveRole)
        ;
    }

    @Override
    public RoleDto updateRole(RoleDto roleDto, Integer id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));
        role.setRoleName(roleDto.getRoleName());
        Role updateRole = roleRepository.save(role);
        return roleMapper.toRoleDto(updateRole);
    }


    @Override
    public RoleDto getRoleById(Integer id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        return roleMapper.toRoleDto(role);
    }

    @Override
    public void deleteRoleById(Integer id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));
        roleRepository.delete(role);
    }

    @Override
    public Page<RoleDto> getAllRoles(Pageable pageable) {
        Page<Role> roles = roleRepository.findAll(pageable);
        return roles.map(roleMapper::toRoleDto);
    }

    @Override
    public List<RoleDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toRoleDto).toList();
    }
}

