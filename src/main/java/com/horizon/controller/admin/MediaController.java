package com.horizon.controller.admin;

import com.horizon.dto.MediaDto;
import com.horizon.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/medias")
public class MediaController {
    @Autowired
    private MediaService mediaService;

    @PostMapping("/create")
    public ResponseEntity<MediaDto> create(@RequestParam("file") MultipartFile file,
                                           @RequestParam Integer roomTypeId) {
        MediaDto mediaDto = mediaService.create(file, roomTypeId);
        return ResponseEntity.ok(mediaDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MediaDto> update(@PathVariable Long id,
                                           @RequestParam("file") MultipartFile file) {
        MediaDto mediaDto = mediaService.update(id, file);
        return ResponseEntity.ok(mediaDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mediaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<MediaDto>> getAll() {
        List<MediaDto> mediaDto = mediaService.getAll();
        return ResponseEntity.ok(mediaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediaDto> getById(@PathVariable Long id) {
        MediaDto mediaDto = mediaService.getById(id);
        return ResponseEntity.ok(mediaDto);
    }

    @GetMapping("/roomType/{roomTypeId}")
    public ResponseEntity<List<MediaDto>> getByRoomTypeId(@PathVariable Integer roomTypeId) {
        List<MediaDto> mediaDto = mediaService.getByRoomTypeId(roomTypeId);
        return ResponseEntity.ok(mediaDto);
    }

    @GetMapping("/roomName/{roomName}")
    public ResponseEntity<List<MediaDto>> getByRoomName(@PathVariable String roomName) {
        List<MediaDto> mediaDto = mediaService.getByRoomName(roomName);
        return ResponseEntity.ok(mediaDto);
    }
}
