package com.horizon.service;

import com.horizon.dto.UtilitiesDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UtilitiesService {

    UtilitiesDto create(UtilitiesDto utilitiesDto);
    UtilitiesDto update(Integer utilitiesId, UtilitiesDto utilitiesDto);
    void delete(Integer utilitiesId);
    UtilitiesDto getById(Integer utilitiesId);
    Page<UtilitiesDto> getAll(Pageable pageable);
    Page<UtilitiesDto> getByName(String name, Pageable pageable);
}
