package com.horizon.controller.home;


import com.horizon.domain.Account;
import com.horizon.repository.AccountRepository;
import com.horizon.response.ResponseObject;
import com.horizon.service.AccountService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("HomeAccountController")
@AllArgsConstructor
@CrossOrigin
@RequestMapping("${spring.application.api-prefix-home}/accounts")
public class AccountController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @PostMapping("/register")
    public ResponseObject<?> register(@RequestParam String email,
                                      @RequestParam String password,
                                      @RequestParam String firstName,
                                      @RequestParam String lastName) {
        try {
            accountService.register(email, password, firstName, lastName);
            return new ResponseObject<>(HttpStatus.OK, "Success", "User registered successfully!");
        } catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseObject<?> login(@RequestParam String email,
                                   @RequestParam String password,
                                   HttpSession session) {
        try {
            String response = accountService.login(email, password, session);
            return new ResponseObject<>(HttpStatus.OK, "Success", response);
        } catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }

    @GetMapping("/current-user")
    public ResponseObject<?> getCurrentUser(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return new ResponseObject<>(HttpStatus.UNAUTHORIZED, "Failed", "No user logged in");
        }
        Account account = accountRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return new ResponseObject<>(HttpStatus.OK, "Success", account);
    }

    @PostMapping("/logout")
    public ResponseObject<?> logout(HttpSession session) {
        accountService.logout(session);
        return new ResponseObject<>(HttpStatus.OK, "Success", "Logged out successfully!");
    }

    @PostMapping("/change-password")
    public ResponseObject<?> changePassword(
            @RequestParam String oldPassword,
            @RequestParam String newPassword,
            HttpSession session) {
        try {
            Boolean response = accountService.changePassword(oldPassword, newPassword, session);
            return new ResponseObject<>(HttpStatus.OK, "Success", response);
        } catch (IllegalArgumentException e) {
            return new ResponseObject<>(HttpStatus.BAD_REQUEST, "Failed", e.getMessage());
        }
    }


}
