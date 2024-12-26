package com.horizon.controller.admin;

import com.horizon.dto.ServicesDto;
import com.horizon.response.ResponseObject;
import com.horizon.service.RoomTypeServicesService;
import com.horizon.service.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-admin}/services")
public class ServicesController {
    private ServicesService servicesService;
    private RoomTypeServicesService rtServicesService;


    @PostMapping
    public ResponseObject<?> create(@RequestBody ServicesDto servicesDto) {
        ServicesDto saveServices = servicesService.create(servicesDto);
        return new ResponseObject<>(HttpStatus.CREATED, "Added successfully!", saveServices);
    }

    @PutMapping("{id}")
    public ResponseObject<?> update(@PathVariable("id") Integer servicesId, @RequestBody ServicesDto updateServices) {
        ServicesDto servicesDto = servicesService.update(servicesId, updateServices);
        return new ResponseObject<>(HttpStatus.OK, "Updated successfully!", servicesDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer servicesId) {
        servicesService.delete(servicesId);
        return new ResponseEntity<>("Services deleted successfully", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ServicesDto> getById(@PathVariable("id") Integer servicesId) {
        ServicesDto servicesDto = servicesService.getById(servicesId);
        return ResponseEntity.ok(servicesDto);
    }

    @GetMapping("/by-name")
    public ResponseEntity<List<ServicesDto>> getByName(@RequestParam String name) {
        List<ServicesDto> listServices = servicesService.getByName(name);
        return ResponseEntity.ok(listServices);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ServicesDto>> getAll() {
        List<ServicesDto> listServices = servicesService.getAll();
        return ResponseEntity.ok(listServices);
    }




    @PostMapping("/update")
    public ResponseEntity<String> updateServicesForRoomType(@RequestParam Integer roomTypeId, @RequestParam String listServices) {
        try {
            rtServicesService.updateServicesForRoomType(roomTypeId, listServices);
            return ResponseEntity.ok("Services updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/getAllRTS")
    public List<Map<String, Object>> getAllRoomTypeService() {
        return rtServicesService.getAll();
    }

    @GetMapping("/by-rtId/{id}")
    public Map<String, Object> getByRoomTypeId(@PathVariable("id") Integer roomTypeId) {
        return rtServicesService.getByRoomTypeId(roomTypeId);
    }

}
