package io.moya.kafkaconsumerpoc.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "test")
    public void consume(String message) throws IOException {
        logger.info(String.format("Consumed message -> %s", message));
    }
}
