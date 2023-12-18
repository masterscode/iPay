package com.Index.service.queue;


import com.Index.entities.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@KafkaListener(topics = "NOTIFICATION")
@RequiredArgsConstructor
public class QueueService {

    @KafkaHandler
    public void transactionHandler(@Payload Transaction transaction) {
        log.info("Using Transaction handler ");
        log.info("--> Received payload :: {}", transaction);
    }

    @KafkaHandler(isDefault = true)
    public void defaultHandler(@Payload Object message) {
        log.info("Using default handler ");

    }

}
