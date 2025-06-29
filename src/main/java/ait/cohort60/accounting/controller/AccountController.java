package ait.cohort60.accounting.controller;

import ait.cohort60.accounting.dto.UserRegisterDto;
import ait.cohort60.accounting.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PatchMapping("/register")
    public ResponseEntity<Void> register (@RequestBody UserRegisterDto dto) {
        accountService.register(dto);
        return ResponseEntity.ok().build();
    }
}
