package com.horizon.controller.admin;

import com.horizon.dto.RoleDto;
import com.horizon.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/admin/roles")
public class RoleController {

    private RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto) {
        RoleDto role = roleService.createRole(roleDto);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>>  getAllRoles() {
        List<RoleDto> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable("id") Integer roleId) {
        RoleDto roleDto = roleService.getRoleById(roleId);
        return ResponseEntity.ok(roleDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<RoleDto> updateRole(@PathVariable("id") Integer roleId, @RequestBody RoleDto updateRole) {
        RoleDto roleDto = roleService.updateRole(updateRole, roleId);
        return ResponseEntity.ok(roleDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") Integer roleId) {
        roleService.deleteRoleById(roleId);
        return new ResponseEntity<>("Role deleted successfully", HttpStatus.OK);
    }
}
