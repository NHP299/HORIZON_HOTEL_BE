package com.horizon.service;

import com.horizon.dto.AccountDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AccountService {
    void register(String email, String password, String firstName, String lastName);

    String login(String email, String rawPassword, HttpSession session);

    void logout(HttpSession session);

    boolean changePassword(String oldPassword, String newPassword, HttpSession session);

    AccountDto create(AccountDto accountDto, MultipartFile profilePicture);

    AccountDto update(Integer id, AccountDto accountDto, MultipartFile profilePicture);

    void delete(Integer id);

    List<AccountDto> getAll();

    AccountDto parseAccountDto(String accountDtoJson);


}
