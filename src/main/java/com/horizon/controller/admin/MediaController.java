package com.horizon.controller.admin;

import com.horizon.dto.MediaDto;
import com.horizon.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/medias")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @PostMapping("/upload/{roomTypeId}")
    public ResponseEntity<Map<String, Object>> uploadMediaFile(@RequestParam("file") MultipartFile file,
                                                               @PathVariable Integer roomTypeId) {
        Map<String, Object> response = new HashMap<>();
        try {
            MediaDto uploadedMedia = mediaService.storeMediaFile(file, roomTypeId);
            response.put("message", "Media uploaded successfully.");
            response.put("media", uploadedMedia);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IOException e) {
            response.put("message", "Failed to upload media.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } catch (RuntimeException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{mediaId}")
    public ResponseEntity<Map<String, Object>> deleteMedia(@PathVariable Integer mediaId) {
        Map<String, Object> response = new HashMap<>();
        try {
            mediaService.deleteMedia(mediaId);
            response.put("message", "Media deleted successfully.");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (RuntimeException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/roomType/{roomTypeId}")
    public ResponseEntity<List<MediaDto>> getMediaByRoomType(@PathVariable Integer roomTypeId) {
        List<MediaDto> mediaList = mediaService.getMediaByRoomType(roomTypeId);
        return ResponseEntity.ok(mediaList);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MediaDto>> getAllMedia() {
        List<MediaDto> mediaList = mediaService.getAllMedia();
        return ResponseEntity.ok(mediaList);
    }
}
