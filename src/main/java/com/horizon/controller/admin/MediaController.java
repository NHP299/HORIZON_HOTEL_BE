package com.horizon.controller.admin;

import com.horizon.dto.MediaDto;
import com.horizon.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/medias")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @PostMapping("/upload/{roomTypeId}")
    public ResponseEntity<Map<String, Object>> createNewMedia(@RequestParam("file") MultipartFile file,
                                                              @PathVariable Integer roomTypeId) {
        return mediaService.createNewMedia(file, roomTypeId);
    }

    @PutMapping("/update/{mediaId}")
    public ResponseEntity<Map<String, Object>> updateMedia(@PathVariable Integer mediaId,
                                                           @RequestParam("file") MultipartFile file) {
        return mediaService.updateMedia(mediaId, file);
    }

    @DeleteMapping("/{mediaId}")
    public ResponseEntity<Map<String, Object>> deleteMedia(@PathVariable Integer mediaId) {
        return mediaService.deleteMedia(mediaId);
    }

    @GetMapping("/{roomTypeId}")
    public ResponseEntity<List<MediaDto>> getMediaByRoomType(@PathVariable Integer roomTypeId) {
        List<MediaDto> mediaList = mediaService.getMediaByRoomType(roomTypeId);
        return ResponseEntity.ok(mediaList);
    }

    @GetMapping("/media-by-room-name")
    public ResponseEntity<List<MediaDto>> getMediaByRoomName(@RequestParam String roomName) {
        List<MediaDto> mediaList = mediaService.getMediaByRoomName(roomName);
        return ResponseEntity.ok(mediaList);
    }

    @GetMapping("/media/{mediaId}")
    public ResponseEntity<MediaDto> getMediaById(@PathVariable Integer mediaId) {
        MediaDto mediaDto = mediaService.getMediaById(mediaId);
        return ResponseEntity.ok(mediaDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MediaDto>> getAllMedia() {
        List<MediaDto> mediaList = mediaService.getAllMedia();
        return ResponseEntity.ok(mediaList);
    }
}