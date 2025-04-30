package com.example.springkafka.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @Value("${spring.kafka.consumer.group-id}")
    private String consumerGroupId;
    @Value("${kafka.topic.name}")
    private String topic;
//    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
//    public void listen(String message,
//                       @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
//                       @Header(KafkaHeaders.OFFSET) long offset) {
//        System.out.println("[" + Thread.currentThread().getName() + "] >>> Received message: \"" + message + "\" from partition: " + partition + " at offset: " + offset);
//    }

    @KafkaListener(
            topics = "${kafka.topic.name}",
            groupId = "groupA",
            containerFactory = "groupAListenerContainerFactory"
    )
    public void listenGroupA(String message,
                             @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                             @Header(KafkaHeaders.OFFSET) long offset) {
        System.out.printf("[Group A][%s] Received: \"%s\" | Partition: %d | Offset: %d%n",
                Thread.currentThread().getName(), message, partition, offset);
    }

    @KafkaListener(
            topics = "${kafka.topic.name}",
            groupId = "groupB",
            containerFactory = "groupBListenerContainerFactory"
    )
    public void listenGroupB(String message,
                             @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                             @Header(KafkaHeaders.OFFSET) long offset) {
        System.out.printf("[Group B][%s] Received: \"%s\" | Partition: %d | Offset: %d%n",
                Thread.currentThread().getName(), message, partition, offset);
    }
}

