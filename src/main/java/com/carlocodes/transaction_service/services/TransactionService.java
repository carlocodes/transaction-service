package com.carlocodes.transaction_service.services;

import com.carlocodes.transaction_service.dtos.CreateTransactionDto;
import com.carlocodes.transaction_service.dtos.TransactionDto;
import com.carlocodes.transaction_service.entities.Transaction;
import com.carlocodes.transaction_service.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<TransactionDto> getTransactions(Long accountId) throws Exception {
        try {
            List<Transaction> transactions = transactionRepository.findByAccountId(accountId);
            return mapToDto(transactions);
        } catch (Exception e) {
            throw new Exception(String.format("Get transactions for account id: %d failed due to: %s",
                    accountId, e.getMessage()), e);
        }
    }

    private List<TransactionDto> mapToDto(List<Transaction> transactions) {
        return transactions.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private TransactionDto mapToDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setAccountId(transaction.getAccountId());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setTransactionType(transaction.getTransactionType());
        return transactionDto;
    }
}
