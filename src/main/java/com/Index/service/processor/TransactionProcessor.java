package com.Index.service.processor;

import com.Index.payloads.BankTransferRequest;
import com.Index.payloads.BankTransferResponse;
import com.Index.providers.CoreBankingProvider;
import com.Index.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;


@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionProcessor {
    private final Map<String, CoreBankingProvider> coreBankingProvider;
    private final TransactionService transactionService;


    @JmsListener(destination = "TRANSACTION_QUEUE", containerFactory = "myFactory")
    public void receiveMessage(BankTransferRequest request) {

        BankTransferResponse response = coreBankingProvider.get(request.getProvider()).processTransaction(request);

        transactionService.getTransaction(request.getTransactionReference()).ifPresent(transaction -> {

            transaction.setStatus(Optional.ofNullable(response.getStatus()).orElse(""));

            transactionService.saveTransaction(transaction);
        });

    }
}
