package com.cdac.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cdac.custom_exceptions.ResourceNotFoundException;
import com.cdac.dao.AccountRepository;
import com.cdac.dao.UserDao;
import com.cdac.dto.AccountReqDTO;
import com.cdac.dto.AccountRespDTO;
import com.cdac.entities.Account;
import com.cdac.entities.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepo;
	private final UserDao userRepo;

	@Override
	public AccountRespDTO createAccount(AccountReqDTO dto) {
		User user = userRepo.findById(dto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		Account account = new Account();
		account.setName(dto.getName());
		account.setBalance(dto.getBalance());
		account.setType(dto.getType());
		account.setUser(user);

		account = accountRepo.save(account);
		return mapToRespDTO(account);
	}

	@Override
	public AccountRespDTO getAccountById(Long id) {
		Account acc = accountRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account not found"));
		return mapToRespDTO(acc);
	}

	@Override
	public List<AccountRespDTO> getAccountsByUserId(Long userId) {
		return accountRepo.findByUserUserId(userId).stream().map(this::mapToRespDTO).collect(Collectors.toList());
	}

	@Override
	public List<AccountRespDTO> getAllAccounts() {
		return accountRepo.findAll().stream().map(this::mapToRespDTO).collect(Collectors.toList());
	}

	@Override
	public AccountRespDTO updateAccount(Long id, AccountReqDTO dto) {
		Account account = accountRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found"));

		account.setName(dto.getName());
		account.setBalance(dto.getBalance());
		account.setType(dto.getType());

		return mapToRespDTO(accountRepo.save(account));
	}

	@Override
	public void deleteAccount(Long id) {
		Account account = accountRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found"));
		accountRepo.delete(account);
	}

//	private AccountRespDTO mapToRespDTO(Account account) {
//		return AccountRespDTO.builder().accountId(account.getAccountId()).name(account.getName())
//				.balance(account.getBalance()).type(account.getType()).userId(account.getUser().getUserId())
//				.userName(account.getUser().getName()).build();
//	}
	private AccountRespDTO mapToRespDTO(Account account) {
	    AccountRespDTO dto = new AccountRespDTO();
	    dto.setAccountId(account.getAccountId());
	    dto.setName(account.getName());
	    dto.setBalance(account.getBalance());
	    dto.setType(account.getType());

	    if (account.getUser() != null) {
	        dto.setUserId(account.getUser().getUserId());
	        dto.setUserName(account.getUser().getName());
	    } else {
	        dto.setUserId(null);
	        dto.setUserName("Unknown");
	    }

	    return dto;
	}


}
