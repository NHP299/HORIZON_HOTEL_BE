package com.horizon.service.impl;

import com.horizon.domain.Banner;
import com.horizon.repository.BannerRepository;
import com.horizon.service.BannerService;
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
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;

    @Autowired
    private BannerServiceImpl(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        bannerRepository.deleteAllByIdInBatch(integers);
    }

    @Override
    public <S extends Banner, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return bannerRepository.findBy(example, queryFunction);
    }

    @Override
    public void deleteAll(Iterable<? extends Banner> entities) {
        bannerRepository.deleteAll(entities);
    }

    @Override
    public List<Banner> findAll(Sort sort) {
        return bannerRepository.findAll(sort);
    }

    @Override
    public <S extends Banner> Optional<S> findOne(Example<S> example) {
        return bannerRepository.findOne(example);
    }

    @Override
    public <S extends Banner> boolean exists(Example<S> example) {
        return bannerRepository.exists(example);
    }

    @Override
    public <S extends Banner> S save(S entity) {
        return bannerRepository.save(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        bannerRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAllInBatch() {
        bannerRepository.deleteAllInBatch();
    }

    @Override
    public Optional<Banner> findById(Integer integer) {
        return bannerRepository.findById(integer);
    }

    @Override
    @Deprecated
    public Banner getOne(Integer integer) {
        return bannerRepository.getOne(integer);
    }

    @Override
    public void deleteAll() {
        bannerRepository.deleteAll();
    }

    @Override
    @Deprecated
    public Banner getById(Integer integer) {
        return bannerRepository.getById(integer);
    }

    @Override
    public void flush() {
        bannerRepository.flush();
    }

    @Override
    public Page<Banner> findAll(Pageable pageable) {
        return bannerRepository.findAll(pageable);
    }

    @Override
    public <S extends Banner> S saveAndFlush(S entity) {
        return bannerRepository.saveAndFlush(entity);
    }

    @Override
    public List<Banner> findAllById(Iterable<Integer> integers) {
        return bannerRepository.findAllById(integers);
    }

    @Override
    public <S extends Banner> List<S> findAll(Example<S> example) {
        return bannerRepository.findAll(example);
    }

    @Override
    public Banner getReferenceById(Integer integer) {
        return bannerRepository.getReferenceById(integer);
    }

    @Override
    public <S extends Banner> Page<S> findAll(Example<S> example, Pageable pageable) {
        return bannerRepository.findAll(example, pageable);
    }

    @Override
    public boolean existsById(Integer integer) {
        return bannerRepository.existsById(integer);
    }

    @Override
    public <S extends Banner> List<S> saveAllAndFlush(Iterable<S> entities) {
        return bannerRepository.saveAllAndFlush(entities);
    }

    @Override
    public List<Banner> findAll() {
        return bannerRepository.findAll();
    }

    @Override
    public void deleteById(Integer integer) {
        bannerRepository.deleteById(integer);
    }

    @Override
    public <S extends Banner> List<S> findAll(Example<S> example, Sort sort) {
        return bannerRepository.findAll(example, sort);
    }

    @Override
    public void delete(Banner entity) {
        bannerRepository.delete(entity);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Banner> entities) {
        bannerRepository.deleteInBatch(entities);
    }

    @Override
    public <S extends Banner> List<S> saveAll(Iterable<S> entities) {
        return bannerRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return bannerRepository.count();
    }

    @Override
    public void deleteAllInBatch(Iterable<Banner> entities) {
        bannerRepository.deleteAllInBatch(entities);
    }

    @Override
    public <S extends Banner> long count(Example<S> example) {
        return bannerRepository.count(example);
    }
}
