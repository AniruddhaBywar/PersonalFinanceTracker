package com.cdac.service;

import com.cdac.dto.AccountReqDTO;
import com.cdac.dto.AccountRespDTO;

import java.util.List;

public interface AccountService {

    AccountRespDTO createAccount(AccountReqDTO accountReqDTO);

    AccountRespDTO getAccountById(Long id);

    List<AccountRespDTO> getAccountsByUserId(Long userId);

    List<AccountRespDTO> getAllAccounts();

    AccountRespDTO updateAccount(Long id, AccountReqDTO dto);

    void deleteAccount(Long id);
}
