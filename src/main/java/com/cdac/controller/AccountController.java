package com.cdac.controller;

import com.cdac.dto.AccountReqDTO;
import com.cdac.dto.AccountRespDTO;
import com.cdac.service.AccountService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountRespDTO> create(@Valid @RequestBody AccountReqDTO dto) {
        return ResponseEntity.ok(accountService.createAccount(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountRespDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @GetMapping
    public ResponseEntity<List<AccountRespDTO>> getAll() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AccountRespDTO>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.getAccountsByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountRespDTO> update(@PathVariable Long id, @Valid @RequestBody AccountReqDTO dto) {
        return ResponseEntity.ok(accountService.updateAccount(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
