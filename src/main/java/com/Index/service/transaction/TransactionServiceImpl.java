package com.Index.service.transaction;


import com.Index.entities.Transaction;
import com.Index.payloads.ApiResponse;
import com.Index.repos.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;


    @Override
    public Transaction saveTransaction(Transaction t){
        return transactionRepository.save(t);
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
