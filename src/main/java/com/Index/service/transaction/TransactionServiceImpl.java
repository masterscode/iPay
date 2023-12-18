package com.Index.service.transaction;


import com.Index.entities.Transaction;
import com.Index.payloads.ApiResponse;
import com.Index.payloads.BankTransferResponse;
import com.Index.repos.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    @Override
    public BankTransferResponse getTransactionStatus(final String transactionReference) {
        Transaction transaction = transactionRepository.findByTransactionReference(transactionReference).orElseThrow();
        return new BankTransferResponse(transaction);
    }

    @Override
    public Transaction saveTransaction(Transaction t){
        return transactionRepository.save(t);
    }

    @Override
    public Optional<Transaction> getTransaction(final String ref) {
        return transactionRepository.findByTransactionReference(ref);
    }

    @Override
    public boolean transactionExists(final String ref){
        return transactionRepository.existsByTransactionReference(ref);
    }

    @Override
    public ApiResponse findTransaction(final String ref){
        return transactionRepository.findByTransactionReference(ref)
                .map(t-> new ApiResponse(
                        "00", "Retrieved transaction", t
                ))
                .orElseThrow();
    }
}
