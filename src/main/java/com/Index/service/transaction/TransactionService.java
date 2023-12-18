package com.Index.service.transaction;

import com.Index.entities.Transaction;
import com.Index.payloads.ApiResponse;

public interface TransactionService {

    Transaction saveTransaction(Transaction t);

    ApiResponse findTransaction(String ref);
}
