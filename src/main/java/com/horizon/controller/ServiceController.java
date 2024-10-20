package com.horizon.controller;

import com.horizon.dto.ServicesDto;
import com.horizon.service.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/admin/services")
public class ServiceController {
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

    @GetMapping
    public ResponseEntity<List<ServicesDto>> getAllServices() {
        List<ServicesDto> listServices = servicesService.getAllServices();
        return ResponseEntity.ok(listServices);
    }

    @PutMapping("id")
    public ResponseEntity<ServicesDto> updateServices(@PathVariable("id") Integer servicesId, @RequestBody ServicesDto updateServices) {
        ServicesDto servicesDto = servicesService.updateServices(servicesId, updateServices);
        return ResponseEntity.ok(servicesDto);
    }

    @DeleteMapping("id")
    public ResponseEntity<String> deleteServices(@PathVariable("id") Integer servicesId) {
        servicesService.deleteServices(servicesId);
        return new ResponseEntity<>("Services deleted successfully", HttpStatus.OK);
    }
}
