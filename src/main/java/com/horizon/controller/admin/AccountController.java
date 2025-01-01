package com.horizon.controller.admin;

import com.horizon.dto.AccountDto;
import com.horizon.repository.AccountRepository;
import com.horizon.response.ResponseObject;
import com.horizon.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;




@RestController("AdminAccountController")
@AllArgsConstructor
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-admin}/accounts")
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
    public ResponseObject<?> getAll() {
        try {
            List<AccountDto> accounts = accountService.getAll();
            return new ResponseObject<>(HttpStatus.OK, "Success", accounts);
        } catch (Exception e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

}
