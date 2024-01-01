package com.Index.service.processor;

import com.Index.entities.Transaction;
import com.Index.payloads.BankTransferResponse;
import com.Index.providers.CoreBankingProvider;
import com.Index.service.transaction.TransactionService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;


@Slf4j
@Component
@KafkaListener(topics = "TRANSACTION")
@RequiredArgsConstructor
public class TransactionProcessor {
    private final Map<String, CoreBankingProvider> coreBankingProvider;
    private final TransactionService transactionService;
    private final Gson gson;


    @KafkaHandler
    public void transactionHandler(@Payload String json) {
        log.info("Using Transaction handler ");
        log.info("--> Received payload :: {}", json);
        final Transaction transaction = gson.fromJson(json, Transaction.class);

        BankTransferResponse response = coreBankingProvider.get(transaction.getProvider()).processTransaction(transaction);
        transaction.setStatus(Optional.ofNullable(response.getStatus()).orElse("N/A"));
        transactionService.saveTransaction(transaction);
    }

    @KafkaHandler(isDefault = true)
    public void defaultHandler(@Payload Object message) {
        log.info("Using default handler ");
        log.info("--> Received payload :: {}", message);

    }
}
