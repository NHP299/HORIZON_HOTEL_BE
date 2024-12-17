package com.horizon.controller.admin;

import com.horizon.dto.ServicesDto;
import com.horizon.service.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@CrossOrigin
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
    public ResponseEntity<List<ServicesDto>> getAll() {
        List<ServicesDto> listServices = servicesService.getAll();
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
    public ResponseEntity<List<ServicesDto>> getByRoomTypeName(@RequestParam String roomTypeName) {
        List<ServicesDto> servicesDtoPage = servicesService.getByRoomTypeName(roomTypeName);
        return ResponseEntity.ok(servicesDtoPage);
    }

    @GetMapping("/by-room-id/{id}")
    public ResponseEntity<List<ServicesDto>> getByRoomId(@PathVariable("id") Integer roomId) {
        List<ServicesDto> servicesDtoPage = servicesService.getByRoomId(roomId);
        return ResponseEntity.ok(servicesDtoPage);
    }

    @GetMapping("/by-room-name")
    public ResponseEntity<List<ServicesDto>> getByRoomName(@RequestParam String roomName) {
        List<ServicesDto> servicesDtoPage = servicesService.getByRoomName(roomName);
        return ResponseEntity.ok(servicesDtoPage);
    }

}
