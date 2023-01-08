package com.Index.service.queue;


import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

@Slf4j
@Component
public class QueueErrorHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
        log.error("Error Message : {}", t.getMessage());
    }
}
