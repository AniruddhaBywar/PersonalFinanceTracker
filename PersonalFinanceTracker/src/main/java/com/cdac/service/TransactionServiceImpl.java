package com.cdac.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.custom_exceptions.ResourceNotFoundException;
import com.cdac.dao.AccountRepository;
import com.cdac.dao.CategoryRepository;
import com.cdac.dao.TransactionRepository;
import com.cdac.dao.UserDao;
import com.cdac.dto.TransactionReqDTO;
import com.cdac.dto.TransactionRespDTO;
import com.cdac.entities.Account;
import com.cdac.entities.Category;
import com.cdac.entities.Transaction;
import com.cdac.entities.User;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserDao userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public TransactionRespDTO createTransaction(TransactionReqDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + dto.getUserId()));
        
       


        Account account = accountRepository.findById(dto.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with ID: " + dto.getAccountId()));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + dto.getCategoryId()));

        Transaction transaction = new Transaction();
        transaction.setAmount(dto.getAmount());
        transaction.setDate(dto.getDate());
        transaction.setType(dto.getType());
        transaction.setDescription(dto.getDescription());
        transaction.setUser(user);
        transaction.setAccount(account);
        transaction.setCategory(category);

        Transaction savedTransaction = transactionRepository.save(transaction);
        return mapToRespDTO(savedTransaction);
    }

    @Override
    public TransactionRespDTO getTransactionById(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with ID: " + transactionId));
        return mapToRespDTO(transaction);
    }

    @Override
    public List<TransactionRespDTO> getAllTransactions() {
        return transactionRepository.findAll()
                .stream()
                .map(this::mapToRespDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionRespDTO> getTransactionsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        return transactionRepository.findByUser(user)
                .stream()
                .map(this::mapToRespDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionRespDTO updateTransaction(Long transactionId, TransactionReqDTO dto) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with ID: " + transactionId));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + dto.getUserId()));

        Account account = accountRepository.findById(dto.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with ID: " + dto.getAccountId()));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + dto.getCategoryId()));

        transaction.setAmount(dto.getAmount());
        transaction.setDate(dto.getDate());
        transaction.setType(dto.getType());
        transaction.setDescription(dto.getDescription());
        transaction.setUser(user);
        transaction.setAccount(account);
        transaction.setCategory(category);

        Transaction updatedTransaction = transactionRepository.save(transaction);
        return mapToRespDTO(updatedTransaction);
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with ID: " + transactionId));
        transactionRepository.delete(transaction);
    }

    private TransactionRespDTO mapToRespDTO(Transaction transaction) {
        return TransactionRespDTO.builder()
                .transactionId(transaction.getTransactionId())
                .amount(transaction.getAmount())
                .date(transaction.getDate())
                .type(transaction.getType())
                .description(transaction.getDescription())
                .userId(transaction.getUser().getUserId())
                .accountId(transaction.getAccount().getAccountId())
                .categoryId(transaction.getCategory().getCategoryId())
                .build();
    }
}
