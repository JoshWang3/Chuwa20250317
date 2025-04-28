package com.chuwa.demo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

/**
 * ClassName: KafkaSecondConsumerService
 * Package: com.chuwa.demo.service
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/28 16:19
 * @version 1.0
 */
@Service
public class KafkaSecondConsumerService {

    @KafkaListener(topics = "${kafka.topic.name}",
            groupId = "consumer_group_2",
            containerFactory = "secondKafkaListenerContainerFactory")
    public void listen(String message,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                       @Header(KafkaHeaders.OFFSET) long offset) {
        System.out.println("Consumer Group 2 - Thread: " + Thread.currentThread().getName() +
                " received message: " + message +
                " from partition: " + partition +
                " at offset: " + offset);
    }
}
