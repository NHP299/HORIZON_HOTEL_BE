package com.horizon.service.impl;

import com.horizon.repository.RoleRepository;
import com.horizon.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    private RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

}

