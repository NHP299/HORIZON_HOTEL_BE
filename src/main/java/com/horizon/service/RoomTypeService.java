package com.horizon.service;


import com.horizon.domain.RoomType;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface RoomTypeService {
    void deleteAllByIdInBatch(Iterable<Integer> integers);

    void delete(RoomType entity);

    <S extends RoomType> boolean exists(Example<S> example);

    @Deprecated
    void deleteInBatch(Iterable<RoomType> entities);

    List<RoomType> findAll(Sort sort);

    void deleteById(Integer integer);

    <S extends RoomType> long count(Example<S> example);

    void deleteAllInBatch(Iterable<RoomType> entities);

    long count();

    <S extends RoomType> List<S> findAll(Example<S> example);

    <S extends RoomType> List<S> findAll(Example<S> example, Sort sort);

    <S extends RoomType> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends RoomType> S saveAndFlush(S entity);

    <S extends RoomType> List<S> saveAllAndFlush(Iterable<S> entities);

    boolean existsById(Integer integer);

    @Deprecated
    RoomType getById(Integer integer);

    <S extends RoomType> List<S> saveAll(Iterable<S> entities);

    RoomType getReferenceById(Integer integer);

    Optional<RoomType> findById(Integer integer);

    void flush();

    Page<RoomType> findAll(Pageable pageable);

    void deleteAll();

    <S extends RoomType> Optional<S> findOne(Example<S> example);

    void deleteAll(Iterable<? extends RoomType> entities);

    List<RoomType> findAllById(Iterable<Integer> integers);

    @Deprecated
    RoomType getOne(Integer integer);

    <S extends RoomType, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    <S extends RoomType> S save(S entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAllInBatch();

    List<RoomType> findAll();
}
