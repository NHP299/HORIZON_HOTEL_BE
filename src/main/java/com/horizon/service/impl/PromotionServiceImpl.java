package com.horizon.service.impl;

import com.horizon.domain.Promotion;
import com.horizon.repository.PromotionRepository;
import com.horizon.service.PromotionService;
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
public class PromotionServiceImpl implements PromotionService
{
    private final PromotionRepository promotionRepository;

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        promotionRepository.deleteAllByIdInBatch(integers);
    }

    @Override
    public <S extends Promotion, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return promotionRepository.findBy(example, queryFunction);
    }

    @Override
    public void deleteAll(Iterable<? extends Promotion> entities) {
        promotionRepository.deleteAll(entities);
    }

    @Override
    public List<Promotion> findAll(Sort sort) {
        return promotionRepository.findAll(sort);
    }

    @Override
    public <S extends Promotion> Optional<S> findOne(Example<S> example) {
        return promotionRepository.findOne(example);
    }

    @Override
    public <S extends Promotion> boolean exists(Example<S> example) {
        return promotionRepository.exists(example);
    }

    @Override
    public <S extends Promotion> S save(S entity) {
        return promotionRepository.save(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        promotionRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAllInBatch() {
        promotionRepository.deleteAllInBatch();
    }

    @Override
    public Optional<Promotion> findById(Integer integer) {
        return promotionRepository.findById(integer);
    }

    @Override
    @Deprecated
    public Promotion getOne(Integer integer) {
        return promotionRepository.getOne(integer);
    }

    @Override
    public void deleteAll() {
        promotionRepository.deleteAll();
    }

    @Override
    @Deprecated
    public Promotion getById(Integer integer) {
        return promotionRepository.getById(integer);
    }

    @Override
    public void flush() {
        promotionRepository.flush();
    }

    @Override
    public Page<Promotion> findAll(Pageable pageable) {
        return promotionRepository.findAll(pageable);
    }

    @Override
    public <S extends Promotion> S saveAndFlush(S entity) {
        return promotionRepository.saveAndFlush(entity);
    }

    @Override
    public List<Promotion> findAllById(Iterable<Integer> integers) {
        return promotionRepository.findAllById(integers);
    }

    @Override
    public <S extends Promotion> List<S> findAll(Example<S> example) {
        return promotionRepository.findAll(example);
    }

    @Override
    public Promotion getReferenceById(Integer integer) {
        return promotionRepository.getReferenceById(integer);
    }

    @Override
    public <S extends Promotion> Page<S> findAll(Example<S> example, Pageable pageable) {
        return promotionRepository.findAll(example, pageable);
    }

    @Override
    public boolean existsById(Integer integer) {
        return promotionRepository.existsById(integer);
    }

    @Override
    public <S extends Promotion> List<S> saveAllAndFlush(Iterable<S> entities) {
        return promotionRepository.saveAllAndFlush(entities);
    }

    @Override
    public List<Promotion> findAll() {
        return promotionRepository.findAll();
    }

    @Override
    public void deleteById(Integer integer) {
        promotionRepository.deleteById(integer);
    }

    @Override
    public <S extends Promotion> List<S> findAll(Example<S> example, Sort sort) {
        return promotionRepository.findAll(example, sort);
    }

    @Override
    public void delete(Promotion entity) {
        promotionRepository.delete(entity);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Promotion> entities) {
        promotionRepository.deleteInBatch(entities);
    }

    @Override
    public <S extends Promotion> List<S> saveAll(Iterable<S> entities) {
        return promotionRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return promotionRepository.count();
    }

    @Override
    public void deleteAllInBatch(Iterable<Promotion> entities) {
        promotionRepository.deleteAllInBatch(entities);
    }

    @Override
    public <S extends Promotion> long count(Example<S> example) {
        return promotionRepository.count(example);
    }

    @Autowired
    private PromotionServiceImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }


}
