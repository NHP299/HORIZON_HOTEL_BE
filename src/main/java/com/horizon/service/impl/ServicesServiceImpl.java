package com.horizon.service.impl;

import com.horizon.repository.ServicesRepository;
import com.horizon.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServicesServiceImpl implements ServicesService {
    private final ServicesRepository servicesRepository;

    @Autowired
    public ServicesServiceImpl(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

}
