package com.horizon.controller.admin;

import com.horizon.dto.UtilitiesDto;
import com.horizon.service.UtilitiesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/admin/utilities")
public class UtilitiesController {
    private UtilitiesService utilitiesService;

    @PostMapping
    public ResponseEntity<UtilitiesDto> createUtilities(@RequestBody UtilitiesDto utilitiesDto) {
        UtilitiesDto saveUtilities = utilitiesService.createUtilities(utilitiesDto);
        return new ResponseEntity<>(saveUtilities, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UtilitiesDto> getUtilitiesById(@PathVariable("id") Integer utilitiesId) {
        UtilitiesDto utilitiesDto = utilitiesService.getUtilitiesById(utilitiesId);
        return ResponseEntity.ok(utilitiesDto);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<UtilitiesDto>> getAllUtilities(Pageable pageable) {
        Page<UtilitiesDto> listUtilities = utilitiesService.getAllUtilities(pageable);
        return ResponseEntity.ok(listUtilities);
    }

    @PutMapping("{id}")
    public ResponseEntity<UtilitiesDto> updateUtilities(@PathVariable("id") Integer utilitiesId, @RequestBody UtilitiesDto updateUtilities) {
        UtilitiesDto utilitiesDto = utilitiesService.updateUtilities(utilitiesId, updateUtilities);
        return ResponseEntity.ok(utilitiesDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUtilities(@PathVariable("id") Integer utilitiesId) {
        utilitiesService.deleteUtilities(utilitiesId);
        return new ResponseEntity<>("Deleted utilities successfully",HttpStatus.OK);
    }

    @GetMapping("/by-room-type-name")
    public ResponseEntity<Page<UtilitiesDto>> getUtilitiesByRoomTypeName(@RequestParam String roomTypeName, Pageable pageable) {
        Page<UtilitiesDto> utilitiesDtoPage = utilitiesService.getUtilitiesByRoomTypeName(roomTypeName, pageable);
        return ResponseEntity.ok(utilitiesDtoPage);
    }

    @GetMapping("/by-room-id/{id}")
    public ResponseEntity<Page<UtilitiesDto>> getUtilitiesByRoomId(@PathVariable("id") Integer roomId, Pageable pageable) {
        Page<UtilitiesDto> utilitiesDtoPage = utilitiesService.getUtilitiesByRoomId(roomId, pageable);
        return ResponseEntity.ok(utilitiesDtoPage);
    }

    @GetMapping("/by-room-name")
    public ResponseEntity<Page<UtilitiesDto>> getUtilitiesByRoomName(@RequestParam String roomName, Pageable pageable) {
        Page<UtilitiesDto> utilitiesDtoPage = utilitiesService.getUtilitiesByRoomName(roomName, pageable);
        return ResponseEntity.ok(utilitiesDtoPage);
    }

}
