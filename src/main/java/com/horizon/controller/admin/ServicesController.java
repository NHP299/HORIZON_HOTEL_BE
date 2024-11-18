package com.horizon.controller.admin;

import com.horizon.dto.ServicesDto;
import com.horizon.service.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/admin/services")
@Validated
public class ServicesController {
    private ServicesService servicesService;

    @PostMapping
    public ResponseEntity<ServicesDto> create(@RequestBody ServicesDto servicesDto) {
        ServicesDto saveServices = servicesService.create(servicesDto);
        return new ResponseEntity<>(saveServices, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ServicesDto> getById(@PathVariable("id") Integer servicesId) {
        ServicesDto servicesDto = servicesService.getById(servicesId);
        return ResponseEntity.ok(servicesDto);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ServicesDto>> getAll(Pageable pageable) {
        Page<ServicesDto> listServices = servicesService.getAll(pageable);
        return ResponseEntity.ok(listServices);
    }

    @PutMapping("{id}")
    public ResponseEntity<ServicesDto> update(@PathVariable("id") Integer servicesId, @RequestBody ServicesDto updateServices) {
        ServicesDto servicesDto = servicesService.update(servicesId, updateServices);
        return ResponseEntity.ok(servicesDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer servicesId) {
        servicesService.delete(servicesId);
        return new ResponseEntity<>("Services deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/by-room-type-name")
    public ResponseEntity<Page<ServicesDto>> getByRoomTypeName(@RequestParam String roomTypeName, Pageable pageable) {
        Page<ServicesDto> servicesDtoPage = servicesService.getByRoomTypeName(roomTypeName, pageable);
        return ResponseEntity.ok(servicesDtoPage);
    }

    @GetMapping("/by-room-id/{id}")
    public ResponseEntity<Page<ServicesDto>> getByRoomId(@PathVariable("id") Integer roomId, Pageable pageable) {
        Page<ServicesDto> servicesDtoPage = servicesService.getByRoomId(roomId, pageable);
        return ResponseEntity.ok(servicesDtoPage);
    }

    @GetMapping("/by-room-name")
    public ResponseEntity<Page<ServicesDto>> getByRoomName(@RequestParam String roomName, Pageable pageable) {
        Page<ServicesDto> servicesDtoPage = servicesService.getByRoomName(roomName, pageable);
        return ResponseEntity.ok(servicesDtoPage);
    }

}
