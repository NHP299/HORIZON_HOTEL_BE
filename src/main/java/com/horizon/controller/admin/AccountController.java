package com.horizon.controller.admin;

import com.horizon.domain.Account;
import com.horizon.dto.AccountDto;
import com.horizon.mapper.AccountMapper;
import com.horizon.repository.AccountRepository;
import com.horizon.service.AccountService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/admin/accounts")
public class AccountController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @PostMapping
    public ResponseEntity<AccountDto> create(
            @RequestParam("accountDto") String accountDtoJson,
            @RequestParam("profilePicture") MultipartFile profilePicture) {
        AccountDto accountDto = accountService.parseAccountDto(accountDtoJson);
        AccountDto createdAccount = accountService.create(accountDto, profilePicture);
        return ResponseEntity.ok(createdAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> update(
            @PathVariable Integer id,
            @RequestPart("accountDto") String accountDtoJson,
            @RequestPart("profilePicture") MultipartFile profilePicture) {
        AccountDto accountDto = accountService.parseAccountDto(accountDtoJson);
        AccountDto updatedAccount = accountService.update(id, accountDto, profilePicture);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAll() {
        List<AccountDto> accounts = accountService.getAll();
        return ResponseEntity.ok(accounts);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam String email,
                                      @RequestParam String password,
                                      @RequestParam String firstName,
                                      @RequestParam String lastName) {
        try {
            accountService.register(email, password, firstName, lastName);
            return ResponseEntity.ok("User registered successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email,
                                   @RequestParam String password,
                                   HttpSession session) {
        try {
            String response = accountService.login(email, password, session);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No user logged in");
        }
        Account account = accountRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return ResponseEntity.ok(account);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        accountService.logout(session);
        return ResponseEntity.ok("Logged out successfully!");
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestParam String oldPassword,
            @RequestParam String newPassword,
            HttpSession session) {
        try {
            Boolean response = accountService.changePassword(oldPassword, newPassword, session);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
