package com.carlocodes.transaction_service.services;

import com.carlocodes.transaction_service.dtos.CreateTransactionDto;
import com.carlocodes.transaction_service.entities.Transaction;
import com.carlocodes.transaction_service.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void createTransaction(CreateTransactionDto createTransactionDto) throws Exception {
        try {
            Transaction transaction = new Transaction();
            transaction.setAccountId(createTransactionDto.getAccountId());
            transaction.setAmount(createTransactionDto.getAmount());
            transaction.setTransactionType(createTransactionDto.getTransactionType());
            transactionRepository.save(transaction);
        } catch (Exception e) {
            throw new Exception(String.format("Create transaction failed for account id: %d due to: %s",
                    createTransactionDto.getAccountId(), e.getMessage()), e);
        }
    }
}
