package com.horizon.controller.admin;

import com.horizon.dto.MediaDto;
import com.horizon.response.ResponseObject;
import com.horizon.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("${spring.application.api-prefix-admin}/medias")
public class MediaController {
    @Autowired
    private MediaService mediaService;

    @PostMapping("/create")
    public ResponseObject<?> create(@RequestParam("file") MultipartFile file,
                                           @RequestParam Integer roomTypeId) {
        MediaDto mediaDto = mediaService.create(file, roomTypeId);
        return new ResponseObject<>(HttpStatus.OK, "Success", mediaDto);
    }

    @PutMapping("/update/{id}")
    public ResponseObject<?> update(@PathVariable Long id,
                                           @RequestParam("file") MultipartFile file) {
        MediaDto mediaDto = mediaService.update(id, file);
        return new ResponseObject<>(HttpStatus.OK, "Success", mediaDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseObject<?> delete(@PathVariable Long id) {
        mediaService.delete(id);
        return new ResponseObject<>(HttpStatus.OK, "Success", null);
    }

    @GetMapping("/{id}")
    public ResponseObject<?> getById(@PathVariable Long id) {
        MediaDto mediaDto = mediaService.getById(id);
        return new ResponseObject<>(HttpStatus.OK, "Success", mediaDto);
    }

    @GetMapping
    public ResponseObject<?> getAll(Pageable pageable) {
        Page<MediaDto> mediaDto = mediaService.getAll(pageable);
        return new ResponseObject<>(HttpStatus.OK, "Success", mediaDto);
    }

    @GetMapping("/roomType/{roomTypeId}")
    public ResponseObject<?> getByRoomTypeId(@PathVariable Integer roomTypeId, Pageable pageable) {
        Page<MediaDto> mediaDto = mediaService.getByRoomTypeId(roomTypeId, pageable);
        return new ResponseObject<>(HttpStatus.OK, "Success", mediaDto);
    }

    @GetMapping("/roomTypeName")
    public ResponseObject<?> getByRoomTypeName(@RequestParam String roomTypeName, Pageable pageable) {
        Page<MediaDto> mediaDto = mediaService.getByRoomTypeName(roomTypeName, pageable);
        return new ResponseObject<>(HttpStatus.OK, "Success", mediaDto);
    }
}
