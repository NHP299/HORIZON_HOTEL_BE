package com.horizon.controller.admin;

import com.horizon.dto.UtilitiesDto;
import com.horizon.response.ResponseObject;
import com.horizon.service.RoomTypeUtilitiesService;
import com.horizon.service.UtilitiesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-admin}/utilities")
public class UtilitiesController {
    private UtilitiesService utilitiesService;
    private RoomTypeUtilitiesService rtUtilitiesService;

    @PostMapping
    public ResponseObject<?> create(@RequestBody UtilitiesDto utilitiesDto) {
        UtilitiesDto saveUtilities = utilitiesService.create(utilitiesDto);
        if (saveUtilities == null) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Addition failed!", null);
        }
        return new ResponseObject<>(HttpStatus.CREATED, "Added successfully!", saveUtilities);
    }

    @GetMapping("{id}")
    public ResponseEntity<UtilitiesDto> getById(@PathVariable("id") Integer utilitiesId) {
        UtilitiesDto utilitiesDto = utilitiesService.getById(utilitiesId);
        return ResponseEntity.ok(utilitiesDto);
    }

    @GetMapping("/by-name")
    public ResponseEntity<List<UtilitiesDto>> getByName(@RequestParam String name) {
        List<UtilitiesDto> listUtilities = utilitiesService.getByName(name);
        return ResponseEntity.ok(listUtilities);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UtilitiesDto>> getAll() {
        List<UtilitiesDto> listUtilities = utilitiesService.getAll();
        return ResponseEntity.ok(listUtilities);
    }

    @PutMapping("{id}")
    public ResponseObject<?> update(@PathVariable("id") Integer utilitiesId, @RequestBody UtilitiesDto updateUtilities) {
        UtilitiesDto utilitiesDto = utilitiesService.update(utilitiesId, updateUtilities);
        if (utilitiesDto == null) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Update failed!", null);
        }
        return new ResponseObject<>(HttpStatus.OK, "Updated successfully!", utilitiesDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer utilitiesId) {
        utilitiesService.delete(utilitiesId);
        return new ResponseEntity<>("Deleted utilities successfully",HttpStatus.OK);
    }



    @PostMapping("/update")
    public ResponseEntity<String> updateUtilitiesForRoomType(@RequestParam Integer roomTypeId, @RequestParam String listUtilities) {
        try {
            rtUtilitiesService.updateUtilitiesForRoomType(roomTypeId, listUtilities);
            return ResponseEntity.ok("Utilities updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/getAllRTU")
    public List<Map<String, Object>> getAllRoomTypeUtility() {
        return rtUtilitiesService.getAll();
    }

    @GetMapping("/by-rtId/{id}")
    public Map<String, Object> getByRoomTypeId(@PathVariable("id") Integer roomTypeId) {
        return rtUtilitiesService.getByRoomTypeId(roomTypeId);
    }

}
