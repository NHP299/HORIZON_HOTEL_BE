package com.horizon.service.impl;

import com.horizon.domain.BookingDetail;
import com.horizon.repository.BookingDetailRepository;
import com.horizon.service.BookingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class BookingDetailServiceImpl implements BookingDetailService {
    private final BookingDetailRepository bookingDetailRepository;

    @Autowired
    private BookingDetailServiceImpl(BookingDetailRepository bookingDetailRepository) {
        this.bookingDetailRepository = bookingDetailRepository;
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        bookingDetailRepository.deleteAllByIdInBatch(integers);
    }

    @Override
    public <S extends BookingDetail, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return bookingDetailRepository.findBy(example, queryFunction);
    }

    @Override
    public void deleteAll(Iterable<? extends BookingDetail> entities) {
        bookingDetailRepository.deleteAll(entities);
    }

    @Override
    public List<BookingDetail> findAll(Sort sort) {
        return bookingDetailRepository.findAll(sort);
    }

    @Override
    public <S extends BookingDetail> Optional<S> findOne(Example<S> example) {
        return bookingDetailRepository.findOne(example);
    }

    @Override
    public <S extends BookingDetail> boolean exists(Example<S> example) {
        return bookingDetailRepository.exists(example);
    }

    @Override
    public <S extends BookingDetail> S save(S entity) {
        return bookingDetailRepository.save(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        bookingDetailRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAllInBatch() {
        bookingDetailRepository.deleteAllInBatch();
    }

    @Override
    public Optional<BookingDetail> findById(Integer integer) {
        return bookingDetailRepository.findById(integer);
    }

    @Override
    @Deprecated
    public BookingDetail getOne(Integer integer) {
        return bookingDetailRepository.getOne(integer);
    }

    @Override
    public void deleteAll() {
        bookingDetailRepository.deleteAll();
    }

    @Override
    @Deprecated
    public BookingDetail getById(Integer integer) {
        return bookingDetailRepository.getById(integer);
    }

    @Override
    public void flush() {
        bookingDetailRepository.flush();
    }

    @Override
    public Page<BookingDetail> findAll(Pageable pageable) {
        return bookingDetailRepository.findAll(pageable);
    }

    @Override
    public <S extends BookingDetail> S saveAndFlush(S entity) {
        return bookingDetailRepository.saveAndFlush(entity);
    }

    @Override
    public List<BookingDetail> findAllById(Iterable<Integer> integers) {
        return bookingDetailRepository.findAllById(integers);
    }

    @Override
    public <S extends BookingDetail> List<S> findAll(Example<S> example) {
        return bookingDetailRepository.findAll(example);
    }

    @Override
    public BookingDetail getReferenceById(Integer integer) {
        return bookingDetailRepository.getReferenceById(integer);
    }

    @Override
    public <S extends BookingDetail> Page<S> findAll(Example<S> example, Pageable pageable) {
        return bookingDetailRepository.findAll(example, pageable);
    }

    @Override
    public boolean existsById(Integer integer) {
        return bookingDetailRepository.existsById(integer);
    }

    @Override
    public <S extends BookingDetail> List<S> saveAllAndFlush(Iterable<S> entities) {
        return bookingDetailRepository.saveAllAndFlush(entities);
    }

    @Override
    public List<BookingDetail> findAll() {
        return bookingDetailRepository.findAll();
    }

    @Override
    public void deleteById(Integer integer) {
        bookingDetailRepository.deleteById(integer);
    }

    @Override
    public <S extends BookingDetail> List<S> findAll(Example<S> example, Sort sort) {
        return bookingDetailRepository.findAll(example, sort);
    }

    @Override
    public void delete(BookingDetail entity) {
        bookingDetailRepository.delete(entity);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<BookingDetail> entities) {
        bookingDetailRepository.deleteInBatch(entities);
    }

    @Override
    public <S extends BookingDetail> List<S> saveAll(Iterable<S> entities) {
        return bookingDetailRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return bookingDetailRepository.count();
    }

    @Override
    public void deleteAllInBatch(Iterable<BookingDetail> entities) {
        bookingDetailRepository.deleteAllInBatch(entities);
    }

    @Override
    public <S extends BookingDetail> long count(Example<S> example) {
        return bookingDetailRepository.count(example);
    }
}
