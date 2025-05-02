package chuwa.backend.kafkaconsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    @KafkaListener(topics = "chuwa-yyds", groupId = "xiaoran-consumer-group1", concurrency = "5")
    public void consumeMessageGroup1(String message) {
        System.out.println("xiaoran-consumer-group1 Received message: " + message);
    }

    @KafkaListener(topics = "chuwa-yyds", groupId = "xiaoran-consumer-group2", concurrency = "5")
    public void consumeMessageGroup2(String message) {
        System.out.println("xiaoran-consumer-group2 Received message: " + message);
    }
}