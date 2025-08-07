package com.cdac.service;

import com.cdac.dto.TransactionReqDTO;
import com.cdac.dto.TransactionRespDTO;

import java.util.List;

public interface TransactionService {

    TransactionRespDTO createTransaction(TransactionReqDTO dto);

    TransactionRespDTO getTransactionById(Long transactionId);

    List<TransactionRespDTO> getAllTransactions();

    List<TransactionRespDTO> getTransactionsByUserId(Long userId);

    TransactionRespDTO updateTransaction(Long transactionId, TransactionReqDTO dto);

    void deleteTransaction(Long transactionId);
}
