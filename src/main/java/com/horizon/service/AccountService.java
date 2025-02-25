package com.horizon.service;

import com.horizon.dto.AccountDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AccountService {
    void register(String email, String password, String firstName, String lastName);

    HashMap<String, String> login(String email, String rawPassword, HttpSession session);

    void logout(HttpSession session);

    boolean changePassword(String oldPassword, String newPassword, HttpSession session);

    boolean changePassword(String newPassword, Integer accountId);

    AccountDto create(AccountDto accountDto);

    AccountDto update(Integer id, AccountDto accountDto);

    void delete(Integer id);

    Page<AccountDto> getAll(Pageable pageable);

    AccountDto parseAccountDto(String accountDtoJson);


}
