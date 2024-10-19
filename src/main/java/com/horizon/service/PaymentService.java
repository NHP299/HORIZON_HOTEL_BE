package com.horizon.service;

import com.horizon.domain.Payment;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface PaymentService {
    void deleteAllByIdInBatch(Iterable<Integer> integers);

    <S extends Payment, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    void deleteAll(Iterable<? extends Payment> entities);

    List<Payment> findAll(Sort sort);

    <S extends Payment> Optional<S> findOne(Example<S> example);

    <S extends Payment> boolean exists(Example<S> example);

    <S extends Payment> S save(S entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAllInBatch();

    Optional<Payment> findById(Integer integer);

    @Deprecated
    Payment getOne(Integer integer);

    void deleteAll();

    @Deprecated
    Payment getById(Integer integer);

    void flush();

    Page<Payment> findAll(Pageable pageable);

    <S extends Payment> S saveAndFlush(S entity);

    List<Payment> findAllById(Iterable<Integer> integers);

    <S extends Payment> List<S> findAll(Example<S> example);

    Payment getReferenceById(Integer integer);

    <S extends Payment> Page<S> findAll(Example<S> example, Pageable pageable);

    boolean existsById(Integer integer);

    <S extends Payment> List<S> saveAllAndFlush(Iterable<S> entities);

    List<Payment> findAll();

    void deleteById(Integer integer);

    <S extends Payment> List<S> findAll(Example<S> example, Sort sort);

    void delete(Payment entity);

    @Deprecated
    void deleteInBatch(Iterable<Payment> entities);

    <S extends Payment> List<S> saveAll(Iterable<S> entities);

    long count();

    void deleteAllInBatch(Iterable<Payment> entities);

    <S extends Payment> long count(Example<S> example);
}
