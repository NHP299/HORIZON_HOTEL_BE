package com.horizon.service;

import com.horizon.domain.BookingDetail;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface BookingDetailService {
    void deleteAllByIdInBatch(Iterable<Integer> integers);

    <S extends BookingDetail, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    void deleteAll(Iterable<? extends BookingDetail> entities);

    List<BookingDetail> findAll(Sort sort);

    <S extends BookingDetail> Optional<S> findOne(Example<S> example);

    <S extends BookingDetail> boolean exists(Example<S> example);

    <S extends BookingDetail> S save(S entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAllInBatch();

    Optional<BookingDetail> findById(Integer integer);

    @Deprecated
    BookingDetail getOne(Integer integer);

    void deleteAll();

    @Deprecated
    BookingDetail getById(Integer integer);

    void flush();

    Page<BookingDetail> findAll(Pageable pageable);

    <S extends BookingDetail> S saveAndFlush(S entity);

    List<BookingDetail> findAllById(Iterable<Integer> integers);

    <S extends BookingDetail> List<S> findAll(Example<S> example);

    BookingDetail getReferenceById(Integer integer);

    <S extends BookingDetail> Page<S> findAll(Example<S> example, Pageable pageable);

    boolean existsById(Integer integer);

    <S extends BookingDetail> List<S> saveAllAndFlush(Iterable<S> entities);

    List<BookingDetail> findAll();

    void deleteById(Integer integer);

    <S extends BookingDetail> List<S> findAll(Example<S> example, Sort sort);

    void delete(BookingDetail entity);

    @Deprecated
    void deleteInBatch(Iterable<BookingDetail> entities);

    <S extends BookingDetail> List<S> saveAll(Iterable<S> entities);

    long count();

    void deleteAllInBatch(Iterable<BookingDetail> entities);

    <S extends BookingDetail> long count(Example<S> example);
}
