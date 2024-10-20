package com.horizon.service;

import com.horizon.domain.Services;
import com.horizon.dto.ServicesDto;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface ServicesService {

    ServicesDto createServices(ServicesDto servicesDto);
    ServicesDto updateServices(Integer serviceId, ServicesDto updateServices);
    void deleteServices(Integer serviceId);
    ServicesDto getServicesById(Integer serviceId);
    List<ServicesDto> getAllServices();

    void deleteAllByIdInBatch(Iterable<Integer> integers);

    void delete(Services entity);

    <S extends Services> boolean exists(Example<S> example);

    @Deprecated
    void deleteInBatch(Iterable<Services> entities);

    List<Services> findAll(Sort sort);

    void deleteById(Integer integer);

    <S extends Services> long count(Example<S> example);

    void deleteAllInBatch(Iterable<Services> entities);

    long count();

    <S extends Services> List<S> findAll(Example<S> example);

    <S extends Services> List<S> findAll(Example<S> example, Sort sort);

    <S extends Services> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Services> S saveAndFlush(S entity);

    <S extends Services> List<S> saveAllAndFlush(Iterable<S> entities);

    boolean existsById(Integer integer);

    @Deprecated
    Services getById(Integer integer);

    <S extends Services> List<S> saveAll(Iterable<S> entities);

    Services getReferenceById(Integer integer);

    Optional<Services> findById(Integer integer);

    void flush();

    Page<Services> findAll(Pageable pageable);

    void deleteAll();

    <S extends Services> Optional<S> findOne(Example<S> example);

    void deleteAll(Iterable<? extends Services> entities);

    List<Services> findAllById(Iterable<Integer> integers);

    @Deprecated
    Services getOne(Integer integer);

    <S extends Services, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    <S extends Services> S save(S entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAllInBatch();

    List<Services> findAll();
}
