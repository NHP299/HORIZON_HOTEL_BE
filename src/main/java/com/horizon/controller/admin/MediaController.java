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

    //upload new image
    @PostMapping("/upload/{roomTypeId}")
    public ResponseEntity<Map<String, Object>> saveNewMedia(@RequestParam("file") MultipartFile file,
                                                            @PathVariable Integer roomTypeId) {
        return mediaService.saveNewMedia(file, roomTypeId);
    }

    //update image by mediaId and roomTypeRoom corresponding to image
    @PutMapping("/update/{mediaId}")
    public ResponseEntity<Map<String, Object>> updateMedia(@PathVariable Integer mediaId,
                                                           @RequestParam("file") MultipartFile file) {
        return mediaService.updateMedia(mediaId, file);
    }


    //delete image by mediaId
    @DeleteMapping("/{mediaId}")
    public ResponseEntity<Map<String, Object>> deleteMedia(@PathVariable Integer mediaId) {
        return mediaService.deleteMedia(mediaId);
    }

    //get image by roomTypeId
    @GetMapping("/{roomTypeId}")
    public ResponseEntity<List<MediaDto>> getMediaByRoomType(@PathVariable Integer roomTypeId) {
        List<MediaDto> mediaList = mediaService.getMediaByRoomType(roomTypeId);
        return ResponseEntity.ok(mediaList);
    }

    //get all image
    @GetMapping("/all")
    public ResponseEntity<List<MediaDto>> getAllMedia() {
        List<MediaDto> mediaList = mediaService.getAllMedia();
        return ResponseEntity.ok(mediaList);
    }
}
