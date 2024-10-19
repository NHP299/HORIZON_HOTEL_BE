package com.horizon.service;

import com.horizon.domain.Booking;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface BookingService {
    void deleteAllByIdInBatch(Iterable<Integer> integers);

    <S extends Booking, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    void deleteAll(Iterable<? extends Booking> entities);

    List<Booking> findAll(Sort sort);

    <S extends Booking> Optional<S> findOne(Example<S> example);

    <S extends Booking> boolean exists(Example<S> example);

    <S extends Booking> S save(S entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAllInBatch();

    Optional<Booking> findById(Integer integer);

    @Deprecated
    Booking getOne(Integer integer);

    void deleteAll();

    @Deprecated
    Booking getById(Integer integer);

    void flush();

    Page<Booking> findAll(Pageable pageable);

    <S extends Booking> S saveAndFlush(S entity);

    List<Booking> findAllById(Iterable<Integer> integers);

    <S extends Booking> List<S> findAll(Example<S> example);

    Booking getReferenceById(Integer integer);

    <S extends Booking> Page<S> findAll(Example<S> example, Pageable pageable);

    boolean existsById(Integer integer);

    <S extends Booking> List<S> saveAllAndFlush(Iterable<S> entities);

    List<Booking> findAll();

    void deleteById(Integer integer);

    <S extends Booking> List<S> findAll(Example<S> example, Sort sort);

    void delete(Booking entity);

    @Deprecated
    void deleteInBatch(Iterable<Booking> entities);

    <S extends Booking> List<S> saveAll(Iterable<S> entities);

    long count();

    void deleteAllInBatch(Iterable<Booking> entities);

    <S extends Booking> long count(Example<S> example);
}
