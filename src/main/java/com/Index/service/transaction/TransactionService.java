package com.Index.service.transaction;

import com.Index.entities.Transaction;
import com.Index.payloads.BankTransferResponse;

import java.util.Optional;

public interface TransactionService {
    BankTransferResponse getTransactionStatus(String transactionReference);

    Transaction saveTransaction(Transaction t);

    Optional<Transaction> getTransaction(String ref);

    boolean transactionExists(String ref);
}
