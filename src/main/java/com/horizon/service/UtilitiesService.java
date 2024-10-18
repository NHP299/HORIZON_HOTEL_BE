package com.horizon.service;

import com.horizon.domain.Utilities;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface UtilitiesService {
    void deleteAllByIdInBatch(Iterable<Integer> integers);

    void delete(Utilities entity);

    <S extends Utilities> boolean exists(Example<S> example);

    @Deprecated
    void deleteInBatch(Iterable<Utilities> entities);

    List<Utilities> findAll(Sort sort);

    void deleteById(Integer integer);

    <S extends Utilities> long count(Example<S> example);

    void deleteAllInBatch(Iterable<Utilities> entities);

    long count();

    <S extends Utilities> List<S> findAll(Example<S> example);

    <S extends Utilities> List<S> findAll(Example<S> example, Sort sort);

    <S extends Utilities> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Utilities> S saveAndFlush(S entity);

    <S extends Utilities> List<S> saveAllAndFlush(Iterable<S> entities);

    boolean existsById(Integer integer);

    @Deprecated
    Utilities getById(Integer integer);

    <S extends Utilities> List<S> saveAll(Iterable<S> entities);

    Utilities getReferenceById(Integer integer);

    Optional<Utilities> findById(Integer integer);

    void flush();

    Page<Utilities> findAll(Pageable pageable);

    void deleteAll();

    <S extends Utilities> Optional<S> findOne(Example<S> example);

    void deleteAll(Iterable<? extends Utilities> entities);

    List<Utilities> findAllById(Iterable<Integer> integers);

    @Deprecated
    Utilities getOne(Integer integer);

    <S extends Utilities, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    <S extends Utilities> S save(S entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAllInBatch();

    List<Utilities> findAll();
}
