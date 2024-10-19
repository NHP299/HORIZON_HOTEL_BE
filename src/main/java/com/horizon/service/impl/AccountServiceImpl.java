package com.horizon.service.impl;

import com.horizon.domain.Account;
import com.horizon.repository.AccountRepository;
import com.horizon.service.AccountService;
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
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    private AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        accountRepository.deleteAllByIdInBatch(integers);
    }

    @Override
    public <S extends Account, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return accountRepository.findBy(example, queryFunction);
    }

    @Override
    public void deleteAll(Iterable<? extends Account> entities) {
        accountRepository.deleteAll(entities);
    }

    @Override
    public List<Account> findAll(Sort sort) {
        return accountRepository.findAll(sort);
    }

    @Override
    public <S extends Account> Optional<S> findOne(Example<S> example) {
        return accountRepository.findOne(example);
    }

    @Override
    public <S extends Account> boolean exists(Example<S> example) {
        return accountRepository.exists(example);
    }

    @Override
    public <S extends Account> S save(S entity) {
        return accountRepository.save(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        accountRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAllInBatch() {
        accountRepository.deleteAllInBatch();
    }

    @Override
    public Optional<Account> findById(Integer integer) {
        return accountRepository.findById(integer);
    }

    @Override
    @Deprecated
    public Account getOne(Integer integer) {
        return accountRepository.getOne(integer);
    }

    @Override
    public void deleteAll() {
        accountRepository.deleteAll();
    }

    @Override
    @Deprecated
    public Account getById(Integer integer) {
        return accountRepository.getById(integer);
    }

    @Override
    public void flush() {
        accountRepository.flush();
    }

    @Override
    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public <S extends Account> S saveAndFlush(S entity) {
        return accountRepository.saveAndFlush(entity);
    }

    @Override
    public List<Account> findAllById(Iterable<Integer> integers) {
        return accountRepository.findAllById(integers);
    }

    @Override
    public <S extends Account> List<S> findAll(Example<S> example) {
        return accountRepository.findAll(example);
    }

    @Override
    public Account getReferenceById(Integer integer) {
        return accountRepository.getReferenceById(integer);
    }

    @Override
    public <S extends Account> Page<S> findAll(Example<S> example, Pageable pageable) {
        return accountRepository.findAll(example, pageable);
    }

    @Override
    public boolean existsById(Integer integer) {
        return accountRepository.existsById(integer);
    }

    @Override
    public <S extends Account> List<S> saveAllAndFlush(Iterable<S> entities) {
        return accountRepository.saveAllAndFlush(entities);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public void deleteById(Integer integer) {
        accountRepository.deleteById(integer);
    }

    @Override
    public <S extends Account> List<S> findAll(Example<S> example, Sort sort) {
        return accountRepository.findAll(example, sort);
    }

    @Override
    public void delete(Account entity) {
        accountRepository.delete(entity);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Account> entities) {
        accountRepository.deleteInBatch(entities);
    }

    @Override
    public <S extends Account> List<S> saveAll(Iterable<S> entities) {
        return accountRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return accountRepository.count();
    }

    @Override
    public void deleteAllInBatch(Iterable<Account> entities) {
        accountRepository.deleteAllInBatch(entities);
    }

    @Override
    public <S extends Account> long count(Example<S> example) {
        return accountRepository.count(example);
    }
}
