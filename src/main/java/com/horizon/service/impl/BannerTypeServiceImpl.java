package com.horizon.service.impl;

import com.horizon.domain.BannerType;
import com.horizon.repository.BannerTypeRepository;
import com.horizon.service.BannerTypeService;
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
public class BannerTypeServiceImpl implements BannerTypeService {
    private final BannerTypeRepository bannerTypeRepository;

    @Autowired
    private BannerTypeServiceImpl(BannerTypeRepository bannerTypeRepository) {
        this.bannerTypeRepository = bannerTypeRepository;
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        bannerTypeRepository.deleteAllByIdInBatch(integers);
    }

    @Override
    public <S extends BannerType, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return bannerTypeRepository.findBy(example, queryFunction);
    }

    @Override
    public void deleteAll(Iterable<? extends BannerType> entities) {
        bannerTypeRepository.deleteAll(entities);
    }

    @Override
    public List<BannerType> findAll(Sort sort) {
        return bannerTypeRepository.findAll(sort);
    }

    @Override
    public <S extends BannerType> Optional<S> findOne(Example<S> example) {
        return bannerTypeRepository.findOne(example);
    }

    @Override
    public <S extends BannerType> boolean exists(Example<S> example) {
        return bannerTypeRepository.exists(example);
    }

    @Override
    public <S extends BannerType> S save(S entity) {
        return bannerTypeRepository.save(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        bannerTypeRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAllInBatch() {
        bannerTypeRepository.deleteAllInBatch();
    }

    @Override
    public Optional<BannerType> findById(Integer integer) {
        return bannerTypeRepository.findById(integer);
    }

    @Override
    @Deprecated
    public BannerType getOne(Integer integer) {
        return bannerTypeRepository.getOne(integer);
    }

    @Override
    public void deleteAll() {
        bannerTypeRepository.deleteAll();
    }

    @Override
    @Deprecated
    public BannerType getById(Integer integer) {
        return bannerTypeRepository.getById(integer);
    }

    @Override
    public void flush() {
        bannerTypeRepository.flush();
    }

    @Override
    public Page<BannerType> findAll(Pageable pageable) {
        return bannerTypeRepository.findAll(pageable);
    }

    @Override
    public <S extends BannerType> S saveAndFlush(S entity) {
        return bannerTypeRepository.saveAndFlush(entity);
    }

    @Override
    public List<BannerType> findAllById(Iterable<Integer> integers) {
        return bannerTypeRepository.findAllById(integers);
    }

    @Override
    public <S extends BannerType> List<S> findAll(Example<S> example) {
        return bannerTypeRepository.findAll(example);
    }

    @Override
    public BannerType getReferenceById(Integer integer) {
        return bannerTypeRepository.getReferenceById(integer);
    }

    @Override
    public <S extends BannerType> Page<S> findAll(Example<S> example, Pageable pageable) {
        return bannerTypeRepository.findAll(example, pageable);
    }

    @Override
    public boolean existsById(Integer integer) {
        return bannerTypeRepository.existsById(integer);
    }

    @Override
    public <S extends BannerType> List<S> saveAllAndFlush(Iterable<S> entities) {
        return bannerTypeRepository.saveAllAndFlush(entities);
    }

    @Override
    public List<BannerType> findAll() {
        return bannerTypeRepository.findAll();
    }

    @Override
    public void deleteById(Integer integer) {
        bannerTypeRepository.deleteById(integer);
    }

    @Override
    public <S extends BannerType> List<S> findAll(Example<S> example, Sort sort) {
        return bannerTypeRepository.findAll(example, sort);
    }

    @Override
    public void delete(BannerType entity) {
        bannerTypeRepository.delete(entity);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<BannerType> entities) {
        bannerTypeRepository.deleteInBatch(entities);
    }

    @Override
    public <S extends BannerType> List<S> saveAll(Iterable<S> entities) {
        return bannerTypeRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return bannerTypeRepository.count();
    }

    @Override
    public void deleteAllInBatch(Iterable<BannerType> entities) {
        bannerTypeRepository.deleteAllInBatch(entities);
    }

    @Override
    public <S extends BannerType> long count(Example<S> example) {
        return bannerTypeRepository.count(example);
    }
}
