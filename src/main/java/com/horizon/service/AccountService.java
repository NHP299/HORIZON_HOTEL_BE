package com.horizon.service;

import com.horizon.dto.AccountDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AccountService {
    AccountDto create(AccountDto accountDto, MultipartFile profilePicture);
    AccountDto update(Integer id, AccountDto accountDto, MultipartFile profilePicture);
    void delete(Integer id);
    List<AccountDto> getAll();
    AccountDto parseAccountDto(String accountDtoJson);
}
