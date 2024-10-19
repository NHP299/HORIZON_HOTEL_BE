package com.horizon.service;

import com.horizon.domain.Promotion;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface PromotionService {
    void deleteAllByIdInBatch(Iterable<Integer> integers);

    <S extends Promotion, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    void deleteAll(Iterable<? extends Promotion> entities);

    List<Promotion> findAll(Sort sort);

    <S extends Promotion> Optional<S> findOne(Example<S> example);

    <S extends Promotion> boolean exists(Example<S> example);

    <S extends Promotion> S save(S entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAllInBatch();

    Optional<Promotion> findById(Integer integer);

    @Deprecated
    Promotion getOne(Integer integer);

    void deleteAll();

    @Deprecated
    Promotion getById(Integer integer);

    void flush();

    Page<Promotion> findAll(Pageable pageable);

    <S extends Promotion> S saveAndFlush(S entity);

    List<Promotion> findAllById(Iterable<Integer> integers);

    <S extends Promotion> List<S> findAll(Example<S> example);

    Promotion getReferenceById(Integer integer);

    <S extends Promotion> Page<S> findAll(Example<S> example, Pageable pageable);

    boolean existsById(Integer integer);

    <S extends Promotion> List<S> saveAllAndFlush(Iterable<S> entities);

    List<Promotion> findAll();

    void deleteById(Integer integer);

    <S extends Promotion> List<S> findAll(Example<S> example, Sort sort);

    void delete(Promotion entity);

    @Deprecated
    void deleteInBatch(Iterable<Promotion> entities);

    <S extends Promotion> List<S> saveAll(Iterable<S> entities);

    long count();

    void deleteAllInBatch(Iterable<Promotion> entities);

    <S extends Promotion> long count(Example<S> example);
}
