package com.horizon.controller.admin;

import com.horizon.dto.RoleDto;
import com.horizon.response.ResponseObject;
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
@RequestMapping("${spring.application.api-prefix-admin}/roles")
public class RoleController {

    private RoleService roleService;

    @PostMapping
    public ResponseObject<?> createRole(@RequestBody RoleDto roleDto) {
        RoleDto role = roleService.createRole(roleDto);
        return new ResponseObject<>(HttpStatus.OK, "Success", role);
    }

    @GetMapping
    public ResponseObject<?>  getAllRoles() {
        List<RoleDto> roles = roleService.getAllRoles();
        return new ResponseObject<>(HttpStatus.OK, "Success", roles);
    }

    @GetMapping("{id}")
    public ResponseObject<?> getRoleById(@PathVariable("id") Integer roleId) {
        RoleDto roleDto = roleService.getRoleById(roleId);
        return new ResponseObject<>(HttpStatus.OK, "Success", roleDto);
    }

    @PutMapping("{id}")
    public ResponseObject<?> updateRole(@PathVariable("id") Integer roleId, @RequestBody RoleDto updateRole) {
        RoleDto roleDto = roleService.updateRole(updateRole, roleId);
        return new ResponseObject<>(HttpStatus.OK, "Success", roleDto);
    }

    @DeleteMapping("{id}")
    public ResponseObject<?> deleteRole(@PathVariable("id") Integer roleId) {
        roleService.deleteRoleById(roleId);
        return new ResponseObject<>(HttpStatus.OK, "Success", null);
    }
}
