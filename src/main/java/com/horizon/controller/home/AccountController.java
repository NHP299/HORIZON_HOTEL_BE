package com.horizon.controller.home;


import com.horizon.domain.Account;
import com.horizon.dto.AccountDto;
import com.horizon.dto.ChangePasswordRequest;
import com.horizon.repository.AccountRepository;
import com.horizon.response.ResponseObject;
import com.horizon.service.AccountService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController("HomeAccountController")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("${spring.application.api-prefix-home}/accounts")
public class AccountController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @PostMapping("/register")
    public ResponseObject<?> register(@RequestBody AccountDto accountDto) {
        try {
            accountService.register(accountDto.getEmail(), accountDto.getPassword(), accountDto.getFirstName(), accountDto.getLastName());
            return new ResponseObject<>(HttpStatus.OK, "Success", "User registered successfully!");
        } catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Email is already exists!!", e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseObject<?> login(@RequestBody AccountDto accountDto, HttpSession session) {
        try {
            HashMap<String, String> response = accountService.login(accountDto.getEmail(), accountDto.getPassword(), session);
            return new ResponseObject<>(HttpStatus.OK, "Success", response);
        } catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Incorrect Email or Password!!", e.getMessage());
        }
    }

    @GetMapping("/current-user")
    public ResponseObject<?> getCurrentUser(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return new ResponseObject<>(HttpStatus.UNAUTHORIZED, "No user logged in", "Failed");
        }
        Account account = accountRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return new ResponseObject<>(HttpStatus.OK, "Success", account);
    }

    @PostMapping("/logout")
    public ResponseObject<?> logout(HttpSession session) {
        try {
            accountService.logout(session);
            return new ResponseObject<>(HttpStatus.OK, "Success", "Logged out successfully!");
        } catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed to logout!!", e.getMessage());
        }

    }

    @PostMapping("/change-password")
    public ResponseObject<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest, HttpSession session) {
        try {
            accountService.changePassword(changePasswordRequest.getOldPassword(), changePasswordRequest.getNewPassword(), session);
            return new ResponseObject<>(HttpStatus.OK, "Success", "Changed password successfully");
        } catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", "Old Password Incorrect!!");
        }
    }


}
