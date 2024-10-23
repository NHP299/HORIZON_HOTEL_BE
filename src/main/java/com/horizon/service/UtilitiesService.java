package com.horizon.service;

import com.horizon.domain.Utilities;
import com.horizon.dto.UtilitiesDto;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface UtilitiesService {

    UtilitiesDto createUtilities(UtilitiesDto utilitiesDto);
    UtilitiesDto updateUtilities(Integer utilitiesId, UtilitiesDto updateUtilities);
    void deleteUtilities(Integer utilitiesId);
    UtilitiesDto getUtilitiesById(Integer utilitiesId);
    List<UtilitiesDto> getAllUtilities();

}
