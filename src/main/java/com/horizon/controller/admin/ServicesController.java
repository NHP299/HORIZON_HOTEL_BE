package com.horizon.controller.admin;

import com.horizon.dto.ServicesDto;
import com.horizon.response.ResponseObject;
import com.horizon.service.RoomTypeServicesService;
import com.horizon.service.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-admin}/services")
public class ServicesController {
    private ServicesService servicesService;
    private RoomTypeServicesService rtServicesService;


    @PostMapping
    public ResponseObject<?> create(@RequestBody ServicesDto servicesDto) {
        try {
            ServicesDto saveServices = servicesService.create(servicesDto);
            return new ResponseObject<>(HttpStatus.OK, "Success", saveServices);
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseObject<?> update(@PathVariable("id") Integer servicesId, @RequestBody ServicesDto updateServices) {
        try{
            ServicesDto servicesDto = servicesService.update(servicesId, updateServices);
            return new ResponseObject<>(HttpStatus.OK, "Success", servicesDto);
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseObject<?> delete(@PathVariable("id") Integer servicesId) {
        try{
            servicesService.delete(servicesId);
            return new ResponseObject<>(HttpStatus.OK,"Success", null);
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseObject<?> getById(@PathVariable("id") Integer servicesId) {
        try {
            return new ResponseObject<>(HttpStatus.OK,"Success", servicesService.getById(servicesId));
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }

    }

    @GetMapping("/by-name")
    public ResponseObject<?> getByName(@RequestParam String name, Pageable pageable) {
        try {
            return new ResponseObject<>(HttpStatus.OK,"Success", servicesService.getByName(name, pageable));
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseObject<?> getAll(Pageable pageable) {
        try {
            return new ResponseObject<>(HttpStatus.OK,"Success", servicesService.getAll(pageable));
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }




    @PostMapping("/update")
    public ResponseObject<?> updateServicesForRoomType(@RequestParam Integer roomTypeId, @RequestParam String listServices) {
        try {
            rtServicesService.updateServicesForRoomType(roomTypeId, listServices);
            return new ResponseObject<>(HttpStatus.OK,"Success", null);
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @GetMapping("/getAllRTS")
    public ResponseObject<?> getAllRoomTypeService() {
        try {
            return new ResponseObject<>(HttpStatus.OK,"Success", rtServicesService.getAll());
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @GetMapping("/by-rtId/{id}")
    public ResponseObject<?> getByRoomTypeId(@PathVariable("id") Integer roomTypeId) {
        try {
            return new ResponseObject<>(HttpStatus.OK,"Success", rtServicesService.getByRoomTypeId(roomTypeId));
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

}
