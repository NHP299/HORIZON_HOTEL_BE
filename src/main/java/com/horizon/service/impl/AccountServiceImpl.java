package com.horizon.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.horizon.domain.Account;
import com.horizon.domain.Role;
import com.horizon.dto.AccountDto;
import com.horizon.mapper.AccountMapper;
import com.horizon.repository.AccountRepository;
import com.horizon.repository.RoleRepository;
import com.horizon.service.AccountService;
import com.horizon.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CloudinaryService cloudinaryService;
    private final AccountMapper accountMapper;
    private final RoleRepository roleRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, CloudinaryService cloudinaryService, AccountMapper accountMapper, RoleRepository roleRepository) {
        this.accountRepository = accountRepository;
        this.cloudinaryService = cloudinaryService;
        this.accountMapper = accountMapper;
        this.roleRepository = roleRepository;
    }

    public AccountDto parseAccountDto(String accountDtoJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(accountDtoJson, AccountDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Invalid AccountDto JSON: " + e.getMessage(), e);
        }
    }

    @Override
    public AccountDto create(AccountDto accountDto, MultipartFile profilePicture) {
        if (profilePicture != null) {
            String profilePictureUrl = cloudinaryService.upload(profilePicture);
            accountDto.setProfilePicture(profilePictureUrl);
        }

        Account account = accountMapper.toEntity(accountDto);
        Account savedAccount = accountRepository.save(account);

        Role role = roleRepository.findById(accountDto.getRoleId()).orElse(null);
        if (role != null) {
            accountDto.setRoleName(role.getRoleName());
        }

        return accountMapper.toDto(savedAccount);
    }

    @Override
    public AccountDto update(Integer id, AccountDto accountDto, MultipartFile profilePicture) {
        Optional<Account> existingAccountOpt = accountRepository.findById(id);
        if (existingAccountOpt.isPresent()) {
            Account existingAccount = existingAccountOpt.get();

            if (profilePicture != null) {
                cloudinaryService.delete(cloudinaryService.getPublicId(existingAccount.getProfilePicture()));
                String newProfilePictureUrl = cloudinaryService.upload(profilePicture);
                accountDto.setProfilePicture(newProfilePictureUrl);
            }

            existingAccount = accountMapper.toEntity(accountDto);
            existingAccount.setId(id);

            Account updatedAccount = accountRepository.save(existingAccount);

            Role role = roleRepository.findById(accountDto.getRoleId()).orElse(null);
            if (role != null) {
                accountDto.setRoleName(role.getRoleName());
            }

            return accountMapper.toDto(updatedAccount);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        Optional<Account> accountOpt = accountRepository.findById(id);
        accountOpt.ifPresent(account -> {
            cloudinaryService.delete(cloudinaryService.getPublicId(account.getProfilePicture()));
            accountRepository.delete(account);
        });
    }

    @Override
    public List<AccountDto> getAll() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDto> accountDtos = accountMapper.toDtoList(accounts);

        for (AccountDto accountDto : accountDtos) {
            Role role = roleRepository.findById(accountDto.getRoleId()).orElse(null);
            if (role != null) {
                accountDto.setRoleName(role.getRoleName());
            }
        }

        return accountDtos;
    }
}
