package com.horizon.service;

import com.horizon.domain.Banner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface BannerService {
    void deleteAllByIdInBatch(Iterable<Integer> integers);

    <S extends Banner, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    void deleteAll(Iterable<? extends Banner> entities);

    List<Banner> findAll(Sort sort);

    <S extends Banner> Optional<S> findOne(Example<S> example);

    <S extends Banner> boolean exists(Example<S> example);

    <S extends Banner> S save(S entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAllInBatch();

    Optional<Banner> findById(Integer integer);

    @Deprecated
    Banner getOne(Integer integer);

    void deleteAll();

    @Deprecated
    Banner getById(Integer integer);

    void flush();

    Page<Banner> findAll(Pageable pageable);

    <S extends Banner> S saveAndFlush(S entity);

    List<Banner> findAllById(Iterable<Integer> integers);

    <S extends Banner> List<S> findAll(Example<S> example);

    Banner getReferenceById(Integer integer);

    <S extends Banner> Page<S> findAll(Example<S> example, Pageable pageable);

    boolean existsById(Integer integer);

    <S extends Banner> List<S> saveAllAndFlush(Iterable<S> entities);

    List<Banner> findAll();

    void deleteById(Integer integer);

    <S extends Banner> List<S> findAll(Example<S> example, Sort sort);

    void delete(Banner entity);

    @Deprecated
    void deleteInBatch(Iterable<Banner> entities);

    <S extends Banner> List<S> saveAll(Iterable<S> entities);

    long count();

    void deleteAllInBatch(Iterable<Banner> entities);

    <S extends Banner> long count(Example<S> example);
}
