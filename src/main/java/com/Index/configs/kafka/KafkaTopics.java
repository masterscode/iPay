package com.Index.configs.kafka;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KafkaTopics {
    public static final String NOTIFICATION = "NOTIFICATION";
    public static final String TRANSACTION = "TRANSACTION";
}
