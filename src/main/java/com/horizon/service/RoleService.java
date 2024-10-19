package com.horizon.service;

import com.horizon.domain.Role;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface RoleService {
    void deleteAllByIdInBatch(Iterable<Integer> integers);

    <S extends Role, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    void deleteAll(Iterable<? extends Role> entities);

    List<Role> findAll(Sort sort);

    <S extends Role> Optional<S> findOne(Example<S> example);

    <S extends Role> boolean exists(Example<S> example);

    <S extends Role> S save(S entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAllInBatch();

    Optional<Role> findById(Integer integer);

    @Deprecated
    Role getOne(Integer integer);

    void deleteAll();

    @Deprecated
    Role getById(Integer integer);

    void flush();

    Page<Role> findAll(Pageable pageable);

    <S extends Role> S saveAndFlush(S entity);

    List<Role> findAllById(Iterable<Integer> integers);

    <S extends Role> List<S> findAll(Example<S> example);

    Role getReferenceById(Integer integer);

    <S extends Role> Page<S> findAll(Example<S> example, Pageable pageable);

    boolean existsById(Integer integer);

    <S extends Role> List<S> saveAllAndFlush(Iterable<S> entities);

    List<Role> findAll();

    void deleteById(Integer integer);

    <S extends Role> List<S> findAll(Example<S> example, Sort sort);

    void delete(Role entity);

    @Deprecated
    void deleteInBatch(Iterable<Role> entities);

    <S extends Role> List<S> saveAll(Iterable<S> entities);

    long count();

    void deleteAllInBatch(Iterable<Role> entities);

    <S extends Role> long count(Example<S> example);
}
