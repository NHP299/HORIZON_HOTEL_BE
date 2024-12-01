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
    public Account toEntity(AccountDto accountDto) {
        Account account = new Account();
        account.setId(accountDto.getId());
        account.setGoogleId(null);
        account.setEmail(accountDto.getEmail());
        account.setFirstName(accountDto.getFirstName());
        account.setLastName(accountDto.getLastName());
        account.setPhone(accountDto.getPhone());
        account.setAccessToken(null);
        account.setCreatedDate(null);
        account.setLastLogin(null);
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
    public AccountDto toDto(Account account) {
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
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}