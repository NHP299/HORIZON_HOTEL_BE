package com.horizon.mapper;

import com.horizon.domain.Account;
import com.horizon.dto.AccountDto;

import java.util.List;

public interface AccountMapper {
    Account toAccount(AccountDto accountDto);
    AccountDto toAccountDto(Account account);
    List<AccountDto> toDtoList(List<Account> accounts);
}
