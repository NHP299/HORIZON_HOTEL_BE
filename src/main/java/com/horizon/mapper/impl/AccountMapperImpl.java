package com.horizon.mapper.impl;

import com.horizon.domain.Account;
import com.horizon.domain.Role;
import com.horizon.dto.AccountDto;
import com.horizon.mapper.AccountMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account toAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setId(accountDto.getId());
        account.setGoogleId(null);
        account.setEmail(accountDto.getEmail());
        account.setFirstName(accountDto.getFirstName());
        account.setLastName(accountDto.getLastName());
        account.setPassword(accountDto.getPassword());
        account.setPhone(accountDto.getPhone());
        account.setAccessToken(accountDto.getAccessToken());
        account.setCreatedDate(accountDto.getCreatedDate());
        account.setLastLogin(accountDto.getLastLogin());
        account.setGender(accountDto.getGender());
        account.setDateOfBirth(accountDto.getDateOfBirth());
        account.setProfilePicture(accountDto.getProfilePicture());
        account.setIsActivated(accountDto.getIsActivated());

        if (accountDto.getRoleId() != null) {
            Role role = new Role();
            role.setId(accountDto.getRoleId());
            account.setRole(role);
        }

        return account;
    }

    @Override
    public AccountDto toAccountDto(Account account) {
        String roleName = null;
        if (account.getRole() != null) {
            roleName = account.getRole().getRoleName();
        }

        return new AccountDto(
                account.getId(),
                account.getRole() != null ? account.getRole().getId() : null,
                roleName,
                account.getGoogleId(),
                account.getEmail(),
                account.getFirstName(),
                account.getLastName(),
                account.getPassword(),
                account.getPhone(),
                account.getAccessToken(),
                account.getCreatedDate(),
                account.getLastLogin(),
                account.getGender(),
                account.getDateOfBirth(),
                account.getProfilePicture(),
                account.getIsActivated()
        );
    }

    @Override
    public List<AccountDto> toDtoList(List<Account> accounts) {
        return accounts.stream()
                .map(this::toAccountDto)
                .collect(Collectors.toList());
    }
}