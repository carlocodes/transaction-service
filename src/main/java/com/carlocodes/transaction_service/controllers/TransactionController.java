package com.carlocodes.transaction_service.controllers;

import com.carlocodes.transaction_service.dtos.CreateTransactionDto;
import com.carlocodes.transaction_service.dtos.TransactionDto;
import com.carlocodes.transaction_service.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/create-transaction")
    public ResponseEntity<String> createTransaction(@RequestBody CreateTransactionDto createTransactionDto) throws Exception {
        transactionService.createTransaction(createTransactionDto);
        return ResponseEntity.ok("Transaction created!");
    }

    @GetMapping("/get-transactions/{accountId}")
    public ResponseEntity<List<TransactionDto>> getTransactions(@PathVariable Long accountId) throws Exception {
        return ResponseEntity.ok(transactionService.getTransactions(accountId));
    }
}
