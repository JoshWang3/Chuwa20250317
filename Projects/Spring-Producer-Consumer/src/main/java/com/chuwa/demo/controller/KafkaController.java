package com.chuwa.demo.controller;

import com.chuwa.demo.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/publish")
    public String publishMessage(@RequestParam("key") String key, @RequestParam("message") String message) {
        kafkaProducerService.sendMessage(key, message);
        return "Message published successfully";
    }

    @PostMapping("/publish/at-least-once")
    public String publishAtLeastOnce(@RequestParam("key") String key, @RequestParam("message") String message) {
        kafkaProducerService.sendMessageAtLeastOnce(key, message);
        return "Message published with at-least-once guarantee";
    }

    @PostMapping("/publish/at-most-once")
    public String publishAtMostOnce(@RequestParam("key") String key, @RequestParam("message") String message) {
        kafkaProducerService.sendMessageAtMostOnce(key, message);
        return "Message published with at-most-once guarantee";
    }
}