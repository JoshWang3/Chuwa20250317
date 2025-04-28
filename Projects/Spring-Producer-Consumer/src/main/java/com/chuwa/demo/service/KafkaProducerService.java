package com.chuwa.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService {

    @Value("${kafka.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    // Original method
    public void sendMessage(String key, String message) {
        kafkaTemplate.send(topicName, key, message);
    }

    // At-least-once delivery guarantee
    public void sendMessageAtLeastOnce(String key, String message) {
        CompletableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topicName, key, message);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.println("Failed to send message: " + message);
                // Retry logic would go here
                sendMessage(key, message); // Simple retry
            } else {
                System.out.println("Message sent successfully: " + message);
            }
        });
    }

    // At-most-once delivery guarantee
    public void sendMessageAtMostOnce(String key, String message) {
        try {
            kafkaTemplate.send(topicName, key, message);
            System.out.println("Message sent, no confirmation needed: " + message);
        } catch (Exception e) {
            System.err.println("Failed to send message, won't retry: " + message);
            // No retry logic
        }
    }

}
