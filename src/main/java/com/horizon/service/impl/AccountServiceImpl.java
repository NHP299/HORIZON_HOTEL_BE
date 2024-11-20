package com.horizon.service.impl;

import com.horizon.repository.AccountRepository;
import com.horizon.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    private AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


}
