package com.horizon.controller.admin;

import com.horizon.dto.AccountDto;
import com.horizon.repository.AccountRepository;
import com.horizon.response.ResponseObject;
import com.horizon.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @PostMapping("/create")
    public ResponseObject<?> create(@RequestBody AccountDto accountDto) {
        AccountDto createdAccount = accountService.create(accountDto);
        return new ResponseObject<>(HttpStatus.OK, "Success", createdAccount);
    }

    @PutMapping("update/{id}")
    public ResponseObject<?> update(
            @PathVariable Integer id,
            @RequestBody AccountDto accountDto) {
        AccountDto updatedAccount = accountService.update(id, accountDto);
        return new ResponseObject<>(HttpStatus.OK, "Success", updatedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseObject<?> delete(@PathVariable Integer id) {
        accountService.delete(id);
        return new ResponseObject<>(HttpStatus.OK, "Success", null);
    }

    @GetMapping
    public ResponseObject<?> getAll(Pageable pageable) {
        try {
            Page<AccountDto> accounts = accountService.getAll(pageable);
            return new ResponseObject<>(HttpStatus.OK, "Success", accounts);
        } catch (Exception e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

}
