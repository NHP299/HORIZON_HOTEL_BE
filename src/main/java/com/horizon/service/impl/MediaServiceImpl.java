package com.horizon.service.impl;

import com.horizon.domain.Media;
import com.horizon.repository.MediaRepository;
import com.horizon.service.MediaService;
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
public class MediaServiceImpl implements MediaService {
    private final MediaRepository mediaRepository;

    @Autowired
    private MediaServiceImpl(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @Override
    public long count() {
        return mediaRepository.count();
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        mediaRepository.deleteAllByIdInBatch(integers);
    }

    @Override
    public void deleteAllInBatch() {
        mediaRepository.deleteAllInBatch();
    }

    @Override
    public void deleteAllInBatch(Iterable<Media> entities) {
        mediaRepository.deleteAllInBatch(entities);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Media> entities) {
        mediaRepository.deleteInBatch(entities);
    }

    @Override
    public <S extends Media> List<S> findAll(Example<S> example) {
        return mediaRepository.findAll(example);
    }

    @Override
    public <S extends Media> List<S> findAll(Example<S> example, Sort sort) {
        return mediaRepository.findAll(example, sort);
    }

    @Override
    public void flush() {
        mediaRepository.flush();
    }

    @Override
    @Deprecated
    public Media getById(Integer integer) {
        return mediaRepository.getById(integer);
    }

    @Override
    @Deprecated
    public Media getOne(Integer integer) {
        return mediaRepository.getOne(integer);
    }

    @Override
    public Media getReferenceById(Integer integer) {
        return mediaRepository.getReferenceById(integer);
    }

    @Override
    public <S extends Media> List<S> saveAllAndFlush(Iterable<S> entities) {
        return mediaRepository.saveAllAndFlush(entities);
    }

    @Override
    public <S extends Media> S saveAndFlush(S entity) {
        return mediaRepository.saveAndFlush(entity);
    }

    @Override
    public List<Media> findAll() {
        return mediaRepository.findAll();
    }

    @Override
    public List<Media> findAllById(Iterable<Integer> integers) {
        return mediaRepository.findAllById(integers);
    }

    @Override
    public <S extends Media> List<S> saveAll(Iterable<S> entities) {
        return mediaRepository.saveAll(entities);
    }

    @Override
    public void delete(Media entity) {
        mediaRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        mediaRepository.deleteAll();
    }

    @Override
    public void deleteAll(Iterable<? extends Media> entities) {
        mediaRepository.deleteAll(entities);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        mediaRepository.deleteAllById(integers);
    }

    @Override
    public void deleteById(Integer integer) {
        mediaRepository.deleteById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return mediaRepository.existsById(integer);
    }

    @Override
    public Optional<Media> findById(Integer integer) {
        return mediaRepository.findById(integer);
    }

    @Override
    public <S extends Media> S save(S entity) {
        return mediaRepository.save(entity);
    }

    @Override
    public List<Media> findAll(Sort sort) {
        return mediaRepository.findAll(sort);
    }

    @Override
    public Page<Media> findAll(Pageable pageable) {
        return mediaRepository.findAll(pageable);
    }

    @Override
    public <S extends Media> long count(Example<S> example) {
        return mediaRepository.count(example);
    }

    @Override
    public <S extends Media> boolean exists(Example<S> example) {
        return mediaRepository.exists(example);
    }

    @Override
    public <S extends Media> Page<S> findAll(Example<S> example, Pageable pageable) {
        return mediaRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Media, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return mediaRepository.findBy(example, queryFunction);
    }

    @Override
    public <S extends Media> Optional<S> findOne(Example<S> example) {
        return mediaRepository.findOne(example);
    }
}
