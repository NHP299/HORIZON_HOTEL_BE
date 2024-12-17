package com.horizon.controller.admin;

import com.horizon.dto.UtilitiesDto;
import com.horizon.service.UtilitiesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/admin/utilities")
@Validated
public class UtilitiesController {
    private UtilitiesService utilitiesService;

    @PostMapping
    public ResponseEntity<UtilitiesDto> create(@RequestBody UtilitiesDto utilitiesDto) {
        UtilitiesDto saveUtilities = utilitiesService.create(utilitiesDto);
        return new ResponseEntity<>(saveUtilities, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UtilitiesDto> getById(@PathVariable("id") Integer utilitiesId) {
        UtilitiesDto utilitiesDto = utilitiesService.getById(utilitiesId);
        return ResponseEntity.ok(utilitiesDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UtilitiesDto>> getAll() {
        List<UtilitiesDto> listUtilities = utilitiesService.getAll();
        return ResponseEntity.ok(listUtilities);
    }

    @PutMapping("{id}")
    public ResponseEntity<UtilitiesDto> update(@PathVariable("id") Integer utilitiesId, @RequestBody UtilitiesDto updateUtilities) {
        UtilitiesDto utilitiesDto = utilitiesService.update(utilitiesId, updateUtilities);
        return ResponseEntity.ok(utilitiesDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer utilitiesId) {
        utilitiesService.delete(utilitiesId);
        return new ResponseEntity<>("Deleted utilities successfully",HttpStatus.OK);
    }

    @GetMapping("/by-room-type-name")
    public ResponseEntity<List<UtilitiesDto>> getByRoomTypeName(@RequestParam String roomTypeName) {
        List<UtilitiesDto> utilitiesDtoPage = utilitiesService.getByRoomTypeName(roomTypeName);
        return ResponseEntity.ok(utilitiesDtoPage);
    }

    @GetMapping("/by-room-id/{id}")
    public ResponseEntity<List<UtilitiesDto>> getByRoomId(@PathVariable("id") Integer roomId) {
        List<UtilitiesDto> utilitiesDtoPage = utilitiesService.getByRoomId(roomId);
        return ResponseEntity.ok(utilitiesDtoPage);
    }

    @GetMapping("/by-room-name")
    public ResponseEntity<List<UtilitiesDto>> getByRoomName(@RequestParam String roomName) {
        List<UtilitiesDto> utilitiesDtoPage = utilitiesService.getByRoomName(roomName);
        return ResponseEntity.ok(utilitiesDtoPage);
    }

}
