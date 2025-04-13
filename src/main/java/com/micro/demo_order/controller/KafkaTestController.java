package com.micro.demo_order.controller;

import com.micro.demo_order.dto.KafkaMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class KafkaTestController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/kafka/publish")
    public String sendMessage(@RequestBody KafkaMessageRequest request) {
        kafkaTemplate.send(request.getTopic(), request.getMessage());
        return "Message sent to topic: " + request.getTopic();
    }
}
