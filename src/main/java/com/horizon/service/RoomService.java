package com.horizon.service;

import com.horizon.domain.Room;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface RoomService {
    void deleteAllByIdInBatch(Iterable<Integer> integers);

    <S extends Room, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    void deleteAll(Iterable<? extends Room> entities);

    List<Room> findAll(Sort sort);

    <S extends Room> Optional<S> findOne(Example<S> example);

    <S extends Room> boolean exists(Example<S> example);

    <S extends Room> S save(S entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAllInBatch();

    Optional<Room> findById(Integer integer);

    @Deprecated
    Room getOne(Integer integer);

    void deleteAll();

    @Deprecated
    Room getById(Integer integer);

    void flush();

    Page<Room> findAll(Pageable pageable);

    <S extends Room> S saveAndFlush(S entity);

    List<Room> findAllById(Iterable<Integer> integers);

    <S extends Room> List<S> findAll(Example<S> example);

    Room getReferenceById(Integer integer);

    <S extends Room> Page<S> findAll(Example<S> example, Pageable pageable);

    boolean existsById(Integer integer);

    <S extends Room> List<S> saveAllAndFlush(Iterable<S> entities);

    List<Room> findAll();

    void deleteById(Integer integer);

    <S extends Room> List<S> findAll(Example<S> example, Sort sort);

    void delete(Room entity);

    @Deprecated
    void deleteInBatch(Iterable<Room> entities);

    <S extends Room> List<S> saveAll(Iterable<S> entities);

    long count();

    void deleteAllInBatch(Iterable<Room> entities);

    <S extends Room> long count(Example<S> example);
}
