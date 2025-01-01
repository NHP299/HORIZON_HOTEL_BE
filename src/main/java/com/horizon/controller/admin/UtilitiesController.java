package com.horizon.controller.admin;

import com.horizon.dto.UtilitiesDto;
import com.horizon.response.ResponseObject;
import com.horizon.service.RoomTypeUtilitiesService;
import com.horizon.service.UtilitiesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-admin}/utilities")
public class UtilitiesController {
    private UtilitiesService utilitiesService;
    private RoomTypeUtilitiesService rtUtilitiesService;

    @PostMapping
    public ResponseObject<?> create(@RequestBody UtilitiesDto utilitiesDto) {
        try {
            UtilitiesDto saveUtilities = utilitiesService.create(utilitiesDto);
            return new ResponseObject<>(HttpStatus.OK, "Success", saveUtilities);
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseObject<?> update(@PathVariable("id") Integer utilitiesId, @RequestBody UtilitiesDto updateUtilities) {
        try {
            UtilitiesDto utilitiesDto = utilitiesService.update(utilitiesId, updateUtilities);
            return new ResponseObject<>(HttpStatus.OK, "Success", utilitiesDto);
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }

    }

    @DeleteMapping("{id}")
    public ResponseObject<?> delete(@PathVariable("id") Integer utilitiesId) {
        try {
            utilitiesService.delete(utilitiesId);
            return new ResponseObject<>(HttpStatus.OK,"Success", null);
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseObject<?> getById(@PathVariable("id") Integer utilitiesId) {
        try {
            return new ResponseObject<>(HttpStatus.OK,"Success", utilitiesService.getById(utilitiesId));
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @GetMapping("/by-name")
    public ResponseObject<?> getByName(@RequestParam String name) {
        try {
            return new ResponseObject<>(HttpStatus.OK,"Success", utilitiesService.getByName(name));
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseObject<?> getAll() {
        try {
            return new ResponseObject<>(HttpStatus.OK,"Success", utilitiesService.getAll());
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }



    @PostMapping("/update")
    public ResponseObject<?> updateUtilitiesForRoomType(@RequestParam Integer roomTypeId, @RequestParam String listUtilities) {
        try {
            rtUtilitiesService.updateUtilitiesForRoomType(roomTypeId, listUtilities);
            return new ResponseObject<>(HttpStatus.OK,"Success", null);
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @GetMapping("/getAllRTU")
    public ResponseObject<?> getAllRoomTypeUtility() {
        try {
            return new ResponseObject<>(HttpStatus.OK,"Success", rtUtilitiesService.getAll());
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @GetMapping("/by-rtId/{id}")
    public ResponseObject<?> getByRoomTypeId(@PathVariable("id") Integer roomTypeId) {
        try {
            return new ResponseObject<>(HttpStatus.OK,"Success", rtUtilitiesService.getByRoomTypeId(roomTypeId));
        }catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

}
