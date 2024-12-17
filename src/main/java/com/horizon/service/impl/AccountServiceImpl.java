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
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CloudinaryService cloudinaryService;
    private final AccountMapper accountMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountDto parseAccountDto(String accountDtoJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(accountDtoJson, AccountDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Invalid AccountDto JSON: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword, HttpSession session) {
        String email = (String) session.getAttribute("email");

        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(oldPassword, account.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }

        String encodedNewPassword = passwordEncoder.encode(newPassword);

        account.setPassword(encodedNewPassword);
        accountRepository.save(account);

        return true;
    }

    @Override
    public boolean changePassword(String newPassword, Integer accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String encodedNewPassword = passwordEncoder.encode(newPassword);

        account.setPassword(encodedNewPassword);
        accountRepository.save(account);

        return true;
    }

    @Override
    public void register(String email, String password, String firstName, String lastName) {
        if (accountRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already exists!");
        }

        Account account = new Account();
        account.setEmail(email);
        account.setPassword(passwordEncoder.encode(password));
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setIsActivated(true);
        account.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        account.setRole(roleRepository.findById(2).orElse(null));
        accountRepository.save(account);
    }

    @Override
    public String login(String email, String rawPassword, HttpSession session) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Account not found!"));
        account.setLastLogin(new Timestamp(System.currentTimeMillis()));
        if (!passwordEncoder.matches(rawPassword, account.getPassword())) {
            throw new IllegalArgumentException("Invalid password!");
        }

        session.setAttribute("userId", account.getId());
        session.setAttribute("email", account.getEmail());

        return "Login successful!";
    }

    @Override
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @Override
    public AccountDto create(AccountDto accountDto, MultipartFile profilePicture) {
        if (profilePicture != null) {
            String profilePictureUrl = cloudinaryService.upload(profilePicture);
            accountDto.setProfilePicture(profilePictureUrl);
        }

        Account account = accountMapper.toAccount(accountDto);
        Account savedAccount = accountRepository.save(account);

        Role role = roleRepository.findById(accountDto.getRoleId()).orElse(null);
        if (role != null) {
            accountDto.setRoleName(role.getRoleName());
        }

        return accountMapper.toAccountDto(savedAccount);
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

            existingAccount = accountMapper.toAccount(accountDto);
            existingAccount.setId(id);

            Account updatedAccount = accountRepository.save(existingAccount);

            Role role = roleRepository.findById(accountDto.getRoleId()).orElse(null);
            if (role != null) {
                accountDto.setRoleName(role.getRoleName());
            }

            return accountMapper.toAccountDto(updatedAccount);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        Optional<Account> accountOpt = accountRepository.findById(id);
        accountOpt.ifPresent(account -> {
            account.setIsActivated(false);
            accountRepository.save(account);
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
