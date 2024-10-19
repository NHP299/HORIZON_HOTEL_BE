package com.horizon.service.impl;

import com.horizon.domain.Room;
import com.horizon.repository.RoomRepository;
import com.horizon.service.RoomService;
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
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        roomRepository.deleteAllByIdInBatch(integers);
    }

    @Override
    public <S extends Room, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return roomRepository.findBy(example, queryFunction);
    }

    @Override
    public void deleteAll(Iterable<? extends Room> entities) {
        roomRepository.deleteAll(entities);
    }

    @Override
    public List<Room> findAll(Sort sort) {
        return roomRepository.findAll(sort);
    }

    @Override
    public <S extends Room> Optional<S> findOne(Example<S> example) {
        return roomRepository.findOne(example);
    }

    @Override
    public <S extends Room> boolean exists(Example<S> example) {
        return roomRepository.exists(example);
    }

    @Override
    public <S extends Room> S save(S entity) {
        return roomRepository.save(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        roomRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAllInBatch() {
        roomRepository.deleteAllInBatch();
    }

    @Override
    public Optional<Room> findById(Integer integer) {
        return roomRepository.findById(integer);
    }

    @Override
    @Deprecated
    public Room getOne(Integer integer) {
        return roomRepository.getOne(integer);
    }

    @Override
    public void deleteAll() {
        roomRepository.deleteAll();
    }

    @Override
    @Deprecated
    public Room getById(Integer integer) {
        return roomRepository.getById(integer);
    }

    @Override
    public void flush() {
        roomRepository.flush();
    }

    @Override
    public Page<Room> findAll(Pageable pageable) {
        return roomRepository.findAll(pageable);
    }

    @Override
    public <S extends Room> S saveAndFlush(S entity) {
        return roomRepository.saveAndFlush(entity);
    }

    @Override
    public List<Room> findAllById(Iterable<Integer> integers) {
        return roomRepository.findAllById(integers);
    }

    @Override
    public <S extends Room> List<S> findAll(Example<S> example) {
        return roomRepository.findAll(example);
    }

    @Override
    public Room getReferenceById(Integer integer) {
        return roomRepository.getReferenceById(integer);
    }

    @Override
    public <S extends Room> Page<S> findAll(Example<S> example, Pageable pageable) {
        return roomRepository.findAll(example, pageable);
    }

    @Override
    public boolean existsById(Integer integer) {
        return roomRepository.existsById(integer);
    }

    @Override
    public <S extends Room> List<S> saveAllAndFlush(Iterable<S> entities) {
        return roomRepository.saveAllAndFlush(entities);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public void deleteById(Integer integer) {
        roomRepository.deleteById(integer);
    }

    @Override
    public <S extends Room> List<S> findAll(Example<S> example, Sort sort) {
        return roomRepository.findAll(example, sort);
    }

    @Override
    public void delete(Room entity) {
        roomRepository.delete(entity);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Room> entities) {
        roomRepository.deleteInBatch(entities);
    }

    @Override
    public <S extends Room> List<S> saveAll(Iterable<S> entities) {
        return roomRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return roomRepository.count();
    }

    @Override
    public void deleteAllInBatch(Iterable<Room> entities) {
        roomRepository.deleteAllInBatch(entities);
    }

    @Override
    public <S extends Room> long count(Example<S> example) {
        return roomRepository.count(example);
    }
}
