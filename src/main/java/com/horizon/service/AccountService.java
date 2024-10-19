package com.horizon.service;

import com.horizon.domain.Account;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface AccountService {
    void deleteAllByIdInBatch(Iterable<Integer> integers);

    <S extends Account, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    void deleteAll(Iterable<? extends Account> entities);

    List<Account> findAll(Sort sort);

    <S extends Account> Optional<S> findOne(Example<S> example);

    <S extends Account> boolean exists(Example<S> example);

    <S extends Account> S save(S entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAllInBatch();

    Optional<Account> findById(Integer integer);

    @Deprecated
    Account getOne(Integer integer);

    void deleteAll();

    @Deprecated
    Account getById(Integer integer);

    void flush();

    Page<Account> findAll(Pageable pageable);

    <S extends Account> S saveAndFlush(S entity);

    List<Account> findAllById(Iterable<Integer> integers);

    <S extends Account> List<S> findAll(Example<S> example);

    Account getReferenceById(Integer integer);

    <S extends Account> Page<S> findAll(Example<S> example, Pageable pageable);

    boolean existsById(Integer integer);

    <S extends Account> List<S> saveAllAndFlush(Iterable<S> entities);

    List<Account> findAll();

    void deleteById(Integer integer);

    <S extends Account> List<S> findAll(Example<S> example, Sort sort);

    void delete(Account entity);

    @Deprecated
    void deleteInBatch(Iterable<Account> entities);

    <S extends Account> List<S> saveAll(Iterable<S> entities);

    long count();

    void deleteAllInBatch(Iterable<Account> entities);

    <S extends Account> long count(Example<S> example);
}
