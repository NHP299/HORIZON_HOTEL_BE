package com.horizon.controller.admin;

import com.horizon.dto.AccountDto;
import com.horizon.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

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
}
