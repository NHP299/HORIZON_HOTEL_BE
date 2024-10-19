package com.horizon.service;

import com.horizon.domain.BannerType;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface BannerTypeService {
    void deleteAllByIdInBatch(Iterable<Integer> integers);

    <S extends BannerType, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    void deleteAll(Iterable<? extends BannerType> entities);

    List<BannerType> findAll(Sort sort);

    <S extends BannerType> Optional<S> findOne(Example<S> example);

    <S extends BannerType> boolean exists(Example<S> example);

    <S extends BannerType> S save(S entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAllInBatch();

    Optional<BannerType> findById(Integer integer);

    @Deprecated
    BannerType getOne(Integer integer);

    void deleteAll();

    @Deprecated
    BannerType getById(Integer integer);

    void flush();

    Page<BannerType> findAll(Pageable pageable);

    <S extends BannerType> S saveAndFlush(S entity);

    List<BannerType> findAllById(Iterable<Integer> integers);

    <S extends BannerType> List<S> findAll(Example<S> example);

    BannerType getReferenceById(Integer integer);

    <S extends BannerType> Page<S> findAll(Example<S> example, Pageable pageable);

    boolean existsById(Integer integer);

    <S extends BannerType> List<S> saveAllAndFlush(Iterable<S> entities);

    List<BannerType> findAll();

    void deleteById(Integer integer);

    <S extends BannerType> List<S> findAll(Example<S> example, Sort sort);

    void delete(BannerType entity);

    @Deprecated
    void deleteInBatch(Iterable<BannerType> entities);

    <S extends BannerType> List<S> saveAll(Iterable<S> entities);

    long count();

    void deleteAllInBatch(Iterable<BannerType> entities);

    <S extends BannerType> long count(Example<S> example);
}
