package com.horizon.service.impl;

import com.horizon.domain.Payment;
import com.horizon.repository.PaymentRepository;
import com.horizon.service.PaymentService;
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
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        paymentRepository.deleteAllByIdInBatch(integers);
    }

    @Override
    public <S extends Payment, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return paymentRepository.findBy(example, queryFunction);
    }

    @Override
    public void deleteAll(Iterable<? extends Payment> entities) {
        paymentRepository.deleteAll(entities);
    }

    @Override
    public List<Payment> findAll(Sort sort) {
        return paymentRepository.findAll(sort);
    }

    @Override
    public <S extends Payment> Optional<S> findOne(Example<S> example) {
        return paymentRepository.findOne(example);
    }

    @Override
    public <S extends Payment> boolean exists(Example<S> example) {
        return paymentRepository.exists(example);
    }

    @Override
    public <S extends Payment> S save(S entity) {
        return paymentRepository.save(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        paymentRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAllInBatch() {
        paymentRepository.deleteAllInBatch();
    }

    @Override
    public Optional<Payment> findById(Integer integer) {
        return paymentRepository.findById(integer);
    }

    @Override
    @Deprecated
    public Payment getOne(Integer integer) {
        return paymentRepository.getOne(integer);
    }

    @Override
    public void deleteAll() {
        paymentRepository.deleteAll();
    }

    @Override
    @Deprecated
    public Payment getById(Integer integer) {
        return paymentRepository.getById(integer);
    }

    @Override
    public void flush() {
        paymentRepository.flush();
    }

    @Override
    public Page<Payment> findAll(Pageable pageable) {
        return paymentRepository.findAll(pageable);
    }

    @Override
    public <S extends Payment> S saveAndFlush(S entity) {
        return paymentRepository.saveAndFlush(entity);
    }

    @Override
    public List<Payment> findAllById(Iterable<Integer> integers) {
        return paymentRepository.findAllById(integers);
    }

    @Override
    public <S extends Payment> List<S> findAll(Example<S> example) {
        return paymentRepository.findAll(example);
    }

    @Override
    public Payment getReferenceById(Integer integer) {
        return paymentRepository.getReferenceById(integer);
    }

    @Override
    public <S extends Payment> Page<S> findAll(Example<S> example, Pageable pageable) {
        return paymentRepository.findAll(example, pageable);
    }

    @Override
    public boolean existsById(Integer integer) {
        return paymentRepository.existsById(integer);
    }

    @Override
    public <S extends Payment> List<S> saveAllAndFlush(Iterable<S> entities) {
        return paymentRepository.saveAllAndFlush(entities);
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public void deleteById(Integer integer) {
        paymentRepository.deleteById(integer);
    }

    @Override
    public <S extends Payment> List<S> findAll(Example<S> example, Sort sort) {
        return paymentRepository.findAll(example, sort);
    }

    @Override
    public void delete(Payment entity) {
        paymentRepository.delete(entity);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Payment> entities) {
        paymentRepository.deleteInBatch(entities);
    }

    @Override
    public <S extends Payment> List<S> saveAll(Iterable<S> entities) {
        return paymentRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return paymentRepository.count();
    }

    @Override
    public void deleteAllInBatch(Iterable<Payment> entities) {
        paymentRepository.deleteAllInBatch(entities);
    }

    @Override
    public <S extends Payment> long count(Example<S> example) {
        return paymentRepository.count(example);
    }

    @Autowired
    private PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

}
