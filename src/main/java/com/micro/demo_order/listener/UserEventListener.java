package com.micro.demo_order.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener {

    @KafkaListener(topics = "user-events", groupId = "order-service-group")
    public void listenUserEvents(String message) {
        System.err.println("Received User Event in Order Service: " + message);
        // Add your business logic to process the event
    }
}
