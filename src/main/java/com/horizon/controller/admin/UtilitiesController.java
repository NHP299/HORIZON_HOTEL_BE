package com.horizon.controller.admin;

import com.horizon.dto.UtilitiesDto;
import com.horizon.service.UtilitiesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
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
    public ResponseEntity<Page<UtilitiesDto>> getAll(Pageable pageable) {
        Page<UtilitiesDto> listUtilities = utilitiesService.getAll(pageable);
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
    public ResponseEntity<Page<UtilitiesDto>> getByRoomTypeName(@RequestParam String roomTypeName, Pageable pageable) {
        Page<UtilitiesDto> utilitiesDtoPage = utilitiesService.getByRoomTypeName(roomTypeName, pageable);
        return ResponseEntity.ok(utilitiesDtoPage);
    }

    @GetMapping("/by-room-id/{id}")
    public ResponseEntity<Page<UtilitiesDto>> getByRoomId(@PathVariable("id") Integer roomId, Pageable pageable) {
        Page<UtilitiesDto> utilitiesDtoPage = utilitiesService.getByRoomId(roomId, pageable);
        return ResponseEntity.ok(utilitiesDtoPage);
    }

    @GetMapping("/by-room-name")
    public ResponseEntity<Page<UtilitiesDto>> getByRoomName(@RequestParam String roomName, Pageable pageable) {
        Page<UtilitiesDto> utilitiesDtoPage = utilitiesService.getByRoomName(roomName, pageable);
        return ResponseEntity.ok(utilitiesDtoPage);
    }

}
