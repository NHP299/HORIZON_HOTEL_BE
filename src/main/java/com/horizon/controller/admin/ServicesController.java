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
    public ResponseEntity<ServicesDto> createServices(@RequestBody ServicesDto servicesDto) {
        ServicesDto saveServices = servicesService.createServices(servicesDto);
        return new ResponseEntity<>(saveServices, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ServicesDto> getServicesById(@PathVariable("id") Integer servicesId) {
        ServicesDto servicesDto = servicesService.getServicesById(servicesId);
        return ResponseEntity.ok(servicesDto);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ServicesDto>> getAllServices(Pageable pageable) {
        Page<ServicesDto> listServices = servicesService.getAllServices(pageable);
        return ResponseEntity.ok(listServices);
    }

    @PutMapping("{id}")
    public ResponseEntity<ServicesDto> updateServices(@PathVariable("id") Integer servicesId, @RequestBody ServicesDto updateServices) {
        ServicesDto servicesDto = servicesService.updateServices(servicesId, updateServices);
        return ResponseEntity.ok(servicesDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteServices(@PathVariable("id") Integer servicesId) {
        servicesService.deleteServices(servicesId);
        return new ResponseEntity<>("Services deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/by-room-type-name")
    public ResponseEntity<Page<ServicesDto>> getServicesByRoomTypeName(@RequestParam String roomTypeName, Pageable pageable) {
        Page<ServicesDto> servicesDtoPage = servicesService.getServicesByRoomTypeName(roomTypeName, pageable);
        return ResponseEntity.ok(servicesDtoPage);
    }

    @GetMapping("/by-room-id/{id}")
    public ResponseEntity<Page<ServicesDto>> getServicesByRoomId(@PathVariable("id") Integer roomId, Pageable pageable) {
        Page<ServicesDto> servicesDtoPage = servicesService.getServicesByRoomId(roomId, pageable);
        return ResponseEntity.ok(servicesDtoPage);
    }

    @GetMapping("/by-room-name")
    public ResponseEntity<Page<ServicesDto>> getServicesByRoomName(@RequestParam String roomName, Pageable pageable) {
        Page<ServicesDto> servicesDtoPage = servicesService.getServicesByRoomName(roomName, pageable);
        return ResponseEntity.ok(servicesDtoPage);
    }

}
