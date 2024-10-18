package com.horizon.service.impl;

import com.horizon.domain.Utilities;
import com.horizon.repository.UtilitiesRepository;
import com.horizon.service.UtilitiesService;
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
public class UtilitiesServiceImpl implements UtilitiesService {

    private final UtilitiesRepository utilitiesRepository;

    @Autowired
    private UtilitiesServiceImpl(UtilitiesRepository utilitiesRepository) {
        this.utilitiesRepository = utilitiesRepository;
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        utilitiesRepository.deleteAllByIdInBatch(integers);
    }

    @Override
    public void delete(Utilities entity) {
        utilitiesRepository.delete(entity);
    }

    @Override
    public <S extends Utilities> boolean exists(Example<S> example) {
        return utilitiesRepository.exists(example);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Utilities> entities) {
        utilitiesRepository.deleteInBatch(entities);
    }

    @Override
    public List<Utilities> findAll(Sort sort) {
        return utilitiesRepository.findAll(sort);
    }

    @Override
    public void deleteById(Integer integer) {
        utilitiesRepository.deleteById(integer);
    }

    @Override
    public <S extends Utilities> long count(Example<S> example) {
        return utilitiesRepository.count(example);
    }

    @Override
    public void deleteAllInBatch(Iterable<Utilities> entities) {
        utilitiesRepository.deleteAllInBatch(entities);
    }

    @Override
    public long count() {
        return utilitiesRepository.count();
    }

    @Override
    public <S extends Utilities> List<S> findAll(Example<S> example) {
        return utilitiesRepository.findAll(example);
    }

    @Override
    public <S extends Utilities> List<S> findAll(Example<S> example, Sort sort) {
        return utilitiesRepository.findAll(example, sort);
    }

    @Override
    public <S extends Utilities> Page<S> findAll(Example<S> example, Pageable pageable) {
        return utilitiesRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Utilities> S saveAndFlush(S entity) {
        return utilitiesRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends Utilities> List<S> saveAllAndFlush(Iterable<S> entities) {
        return utilitiesRepository.saveAllAndFlush(entities);
    }

    @Override
    public boolean existsById(Integer integer) {
        return utilitiesRepository.existsById(integer);
    }

    @Override
    @Deprecated
    public Utilities getById(Integer integer) {
        return utilitiesRepository.getById(integer);
    }

    @Override
    public <S extends Utilities> List<S> saveAll(Iterable<S> entities) {
        return utilitiesRepository.saveAll(entities);
    }

    @Override
    public Utilities getReferenceById(Integer integer) {
        return utilitiesRepository.getReferenceById(integer);
    }

    @Override
    public Optional<Utilities> findById(Integer integer) {
        return utilitiesRepository.findById(integer);
    }

    @Override
    public void flush() {
        utilitiesRepository.flush();
    }

    @Override
    public Page<Utilities> findAll(Pageable pageable) {
        return utilitiesRepository.findAll(pageable);
    }

    @Override
    public void deleteAll() {
        utilitiesRepository.deleteAll();
    }

    @Override
    public <S extends Utilities> Optional<S> findOne(Example<S> example) {
        return utilitiesRepository.findOne(example);
    }

    @Override
    public void deleteAll(Iterable<? extends Utilities> entities) {
        utilitiesRepository.deleteAll(entities);
    }

    @Override
    public List<Utilities> findAllById(Iterable<Integer> integers) {
        return utilitiesRepository.findAllById(integers);
    }

    @Override
    @Deprecated
    public Utilities getOne(Integer integer) {
        return utilitiesRepository.getOne(integer);
    }

    @Override
    public <S extends Utilities, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return utilitiesRepository.findBy(example, queryFunction);
    }

    @Override
    public <S extends Utilities> S save(S entity) {
        return utilitiesRepository.save(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        utilitiesRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAllInBatch() {
        utilitiesRepository.deleteAllInBatch();
    }

    @Override
    public List<Utilities> findAll() {
        return utilitiesRepository.findAll();
    }
}
