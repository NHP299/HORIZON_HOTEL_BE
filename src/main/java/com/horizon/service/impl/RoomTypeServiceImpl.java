package com.horizon.service.impl;

import com.horizon.domain.RoomType;
import com.horizon.repository.RoomTypeRepository;
import com.horizon.service.RoomTypeService;
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
public class RoomTypeServiceImpl implements RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;

    @Autowired
    private RoomTypeServiceImpl(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        roomTypeRepository.deleteAllByIdInBatch(integers);
    }

    @Override
    public void delete(RoomType entity) {
        roomTypeRepository.delete(entity);
    }

    @Override
    public <S extends RoomType> boolean exists(Example<S> example) {
        return roomTypeRepository.exists(example);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<RoomType> entities) {
        roomTypeRepository.deleteInBatch(entities);
    }

    @Override
    public List<RoomType> findAll(Sort sort) {
        return roomTypeRepository.findAll(sort);
    }

    @Override
    public void deleteById(Integer integer) {
        roomTypeRepository.deleteById(integer);
    }

    @Override
    public <S extends RoomType> long count(Example<S> example) {
        return roomTypeRepository.count(example);
    }

    @Override
    public void deleteAllInBatch(Iterable<RoomType> entities) {
        roomTypeRepository.deleteAllInBatch(entities);
    }

    @Override
    public long count() {
        return roomTypeRepository.count();
    }

    @Override
    public <S extends RoomType> List<S> findAll(Example<S> example) {
        return roomTypeRepository.findAll(example);
    }

    @Override
    public <S extends RoomType> List<S> findAll(Example<S> example, Sort sort) {
        return roomTypeRepository.findAll(example, sort);
    }

    @Override
    public <S extends RoomType> Page<S> findAll(Example<S> example, Pageable pageable) {
        return roomTypeRepository.findAll(example, pageable);
    }

    @Override
    public <S extends RoomType> S saveAndFlush(S entity) {
        return roomTypeRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends RoomType> List<S> saveAllAndFlush(Iterable<S> entities) {
        return roomTypeRepository.saveAllAndFlush(entities);
    }

    @Override
    public boolean existsById(Integer integer) {
        return roomTypeRepository.existsById(integer);
    }

    @Override
    @Deprecated
    public RoomType getById(Integer integer) {
        return roomTypeRepository.getById(integer);
    }

    @Override
    public <S extends RoomType> List<S> saveAll(Iterable<S> entities) {
        return roomTypeRepository.saveAll(entities);
    }

    @Override
    public RoomType getReferenceById(Integer integer) {
        return roomTypeRepository.getReferenceById(integer);
    }

    @Override
    public Optional<RoomType> findById(Integer integer) {
        return roomTypeRepository.findById(integer);
    }

    @Override
    public void flush() {
        roomTypeRepository.flush();
    }

    @Override
    public Page<RoomType> findAll(Pageable pageable) {
        return roomTypeRepository.findAll(pageable);
    }

    @Override
    public void deleteAll() {
        roomTypeRepository.deleteAll();
    }

    @Override
    public <S extends RoomType> Optional<S> findOne(Example<S> example) {
        return roomTypeRepository.findOne(example);
    }

    @Override
    public void deleteAll(Iterable<? extends RoomType> entities) {
        roomTypeRepository.deleteAll(entities);
    }

    @Override
    public List<RoomType> findAllById(Iterable<Integer> integers) {
        return roomTypeRepository.findAllById(integers);
    }

    @Override
    @Deprecated
    public RoomType getOne(Integer integer) {
        return roomTypeRepository.getOne(integer);
    }

    @Override
    public <S extends RoomType, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return roomTypeRepository.findBy(example, queryFunction);
    }

    @Override
    public <S extends RoomType> S save(S entity) {
        return roomTypeRepository.save(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        roomTypeRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAllInBatch() {
        roomTypeRepository.deleteAllInBatch();
    }

    @Override
    public List<RoomType> findAll() {
        return roomTypeRepository.findAll();
    }
}
