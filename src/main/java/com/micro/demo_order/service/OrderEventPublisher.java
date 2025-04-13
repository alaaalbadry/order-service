package com.micro.demo_order.service;

import com.micro.demo_order.saga.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventPublisher {

    private static final String TOPIC = "order-created";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publishOrderCreatedEvent(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}