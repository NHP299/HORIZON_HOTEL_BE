package com.horizon.service;

import com.horizon.domain.Media;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface MediaService {

    long count();

    void deleteAllByIdInBatch(Iterable<Integer> integers);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<Media> entities);

    @Deprecated
    void deleteInBatch(Iterable<Media> entities);

    <S extends Media> List<S> findAll(Example<S> example);

    <S extends Media> List<S> findAll(Example<S> example, Sort sort);

    void flush();

    @Deprecated
    Media getById(Integer integer);

    @Deprecated
    Media getOne(Integer integer);

    Media getReferenceById(Integer integer);

    <S extends Media> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends Media> S saveAndFlush(S entity);

    List<Media> findAll();

    List<Media> findAllById(Iterable<Integer> integers);

    <S extends Media> List<S> saveAll(Iterable<S> entities);

    void delete(Media entity);

    void deleteAll();

    void deleteAll(Iterable<? extends Media> entities);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteById(Integer integer);

    boolean existsById(Integer integer);

    Optional<Media> findById(Integer integer);

    <S extends Media> S save(S entity);

    List<Media> findAll(Sort sort);

    Page<Media> findAll(Pageable pageable);

    <S extends Media> long count(Example<S> example);

    <S extends Media> boolean exists(Example<S> example);

    <S extends Media> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Media, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    <S extends Media> Optional<S> findOne(Example<S> example);
}
