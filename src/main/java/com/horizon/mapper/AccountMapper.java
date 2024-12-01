package com.horizon.mapper;

import com.horizon.domain.Account;
import com.horizon.dto.AccountDto;

import java.util.List;

public interface AccountMapper {
    Account toEntity(AccountDto accountDto);
    AccountDto toDto(Account account);
    List<AccountDto> toDtoList(List<Account> accounts);
}
