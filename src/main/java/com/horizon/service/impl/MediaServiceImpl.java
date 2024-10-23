package com.horizon.service.impl;

import com.horizon.repository.MediaRepository;
import com.horizon.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaServiceImpl implements MediaService {
    private final MediaRepository mediaRepository;

    @Autowired
    private MediaServiceImpl(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

}
