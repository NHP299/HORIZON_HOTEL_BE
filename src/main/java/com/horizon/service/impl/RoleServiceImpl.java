package com.horizon.service.impl;

import com.horizon.domain.Role;
import com.horizon.repository.RoleRepository;
import com.horizon.service.RoleService;
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
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    private RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        roleRepository.deleteAllByIdInBatch(integers);
    }

    @Override
    public <S extends Role, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return roleRepository.findBy(example, queryFunction);
    }

    @Override
    public void deleteAll(Iterable<? extends Role> entities) {
        roleRepository.deleteAll(entities);
    }

    @Override
    public List<Role> findAll(Sort sort) {
        return roleRepository.findAll(sort);
    }

    @Override
    public <S extends Role> Optional<S> findOne(Example<S> example) {
        return roleRepository.findOne(example);
    }

    @Override
    public <S extends Role> boolean exists(Example<S> example) {
        return roleRepository.exists(example);
    }

    @Override
    public <S extends Role> S save(S entity) {
        return roleRepository.save(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        roleRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAllInBatch() {
        roleRepository.deleteAllInBatch();
    }

    @Override
    public Optional<Role> findById(Integer integer) {
        return roleRepository.findById(integer);
    }

    @Override
    @Deprecated
    public Role getOne(Integer integer) {
        return roleRepository.getOne(integer);
    }

    @Override
    public void deleteAll() {
        roleRepository.deleteAll();
    }

    @Override
    @Deprecated
    public Role getById(Integer integer) {
        return roleRepository.getById(integer);
    }

    @Override
    public void flush() {
        roleRepository.flush();
    }

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public <S extends Role> S saveAndFlush(S entity) {
        return roleRepository.saveAndFlush(entity);
    }

    @Override
    public List<Role> findAllById(Iterable<Integer> integers) {
        return roleRepository.findAllById(integers);
    }

    @Override
    public <S extends Role> List<S> findAll(Example<S> example) {
        return roleRepository.findAll(example);
    }

    @Override
    public Role getReferenceById(Integer integer) {
        return roleRepository.getReferenceById(integer);
    }

    @Override
    public <S extends Role> Page<S> findAll(Example<S> example, Pageable pageable) {
        return roleRepository.findAll(example, pageable);
    }

    @Override
    public boolean existsById(Integer integer) {
        return roleRepository.existsById(integer);
    }

    @Override
    public <S extends Role> List<S> saveAllAndFlush(Iterable<S> entities) {
        return roleRepository.saveAllAndFlush(entities);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteById(Integer integer) {
        roleRepository.deleteById(integer);
    }

    @Override
    public <S extends Role> List<S> findAll(Example<S> example, Sort sort) {
        return roleRepository.findAll(example, sort);
    }

    @Override
    public void delete(Role entity) {
        roleRepository.delete(entity);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Role> entities) {
        roleRepository.deleteInBatch(entities);
    }

    @Override
    public <S extends Role> List<S> saveAll(Iterable<S> entities) {
        return roleRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return roleRepository.count();
    }

    @Override
    public void deleteAllInBatch(Iterable<Role> entities) {
        roleRepository.deleteAllInBatch(entities);
    }

    @Override
    public <S extends Role> long count(Example<S> example) {
        return roleRepository.count(example);
    }
}

