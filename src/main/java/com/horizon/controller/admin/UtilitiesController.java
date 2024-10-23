package com.horizon.controller.admin;

import com.horizon.dto.UtilitiesDto;
import com.horizon.service.UtilitiesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<UtilitiesDto>> getAllUtilities() {
        List<UtilitiesDto> listUtilities = utilitiesService.getAllUtilities();
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
}
