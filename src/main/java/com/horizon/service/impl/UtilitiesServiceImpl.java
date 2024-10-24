package com.horizon.service.impl;

import com.horizon.repository.UtilitiesRepository;
import com.horizon.service.UtilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilitiesServiceImpl implements UtilitiesService {

    private final UtilitiesRepository utilitiesRepository;

    @Autowired
    private UtilitiesServiceImpl(UtilitiesRepository utilitiesRepository) {
        this.utilitiesRepository = utilitiesRepository;
    }

}
