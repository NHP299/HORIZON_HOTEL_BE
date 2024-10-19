package com.horizon.service.impl;

import com.horizon.domain.Booking;
import com.horizon.repository.BookingRepository;
import com.horizon.service.BookingService;
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
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    @Autowired
    private BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        bookingRepository.deleteAllByIdInBatch(integers);
    }

    @Override
    public <S extends Booking, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return bookingRepository.findBy(example, queryFunction);
    }

    @Override
    public void deleteAll(Iterable<? extends Booking> entities) {
        bookingRepository.deleteAll(entities);
    }

    @Override
    public List<Booking> findAll(Sort sort) {
        return bookingRepository.findAll(sort);
    }

    @Override
    public <S extends Booking> Optional<S> findOne(Example<S> example) {
        return bookingRepository.findOne(example);
    }

    @Override
    public <S extends Booking> boolean exists(Example<S> example) {
        return bookingRepository.exists(example);
    }

    @Override
    public <S extends Booking> S save(S entity) {
        return bookingRepository.save(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        bookingRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAllInBatch() {
        bookingRepository.deleteAllInBatch();
    }

    @Override
    public Optional<Booking> findById(Integer integer) {
        return bookingRepository.findById(integer);
    }

    @Override
    @Deprecated
    public Booking getOne(Integer integer) {
        return bookingRepository.getOne(integer);
    }

    @Override
    public void deleteAll() {
        bookingRepository.deleteAll();
    }

    @Override
    @Deprecated
    public Booking getById(Integer integer) {
        return bookingRepository.getById(integer);
    }

    @Override
    public void flush() {
        bookingRepository.flush();
    }

    @Override
    public Page<Booking> findAll(Pageable pageable) {
        return bookingRepository.findAll(pageable);
    }

    @Override
    public <S extends Booking> S saveAndFlush(S entity) {
        return bookingRepository.saveAndFlush(entity);
    }

    @Override
    public List<Booking> findAllById(Iterable<Integer> integers) {
        return bookingRepository.findAllById(integers);
    }

    @Override
    public <S extends Booking> List<S> findAll(Example<S> example) {
        return bookingRepository.findAll(example);
    }

    @Override
    public Booking getReferenceById(Integer integer) {
        return bookingRepository.getReferenceById(integer);
    }

    @Override
    public <S extends Booking> Page<S> findAll(Example<S> example, Pageable pageable) {
        return bookingRepository.findAll(example, pageable);
    }

    @Override
    public boolean existsById(Integer integer) {
        return bookingRepository.existsById(integer);
    }

    @Override
    public <S extends Booking> List<S> saveAllAndFlush(Iterable<S> entities) {
        return bookingRepository.saveAllAndFlush(entities);
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public void deleteById(Integer integer) {
        bookingRepository.deleteById(integer);
    }

    @Override
    public <S extends Booking> List<S> findAll(Example<S> example, Sort sort) {
        return bookingRepository.findAll(example, sort);
    }

    @Override
    public void delete(Booking entity) {
        bookingRepository.delete(entity);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Booking> entities) {
        bookingRepository.deleteInBatch(entities);
    }

    @Override
    public <S extends Booking> List<S> saveAll(Iterable<S> entities) {
        return bookingRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return bookingRepository.count();
    }

    @Override
    public void deleteAllInBatch(Iterable<Booking> entities) {
        bookingRepository.deleteAllInBatch(entities);
    }

    @Override
    public <S extends Booking> long count(Example<S> example) {
        return bookingRepository.count(example);
    }
}
