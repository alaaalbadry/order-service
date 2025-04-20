package com.micro.demo_order.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.demo_order.saga.OrderCreatedEvent;
import com.micro.demo_order.saga.commands.ReserveInventoryCommand;
import com.micro.demo_order.saga.commands.ValidatePaymentCommand;
import com.micro.demo_order.saga.events.PaymentValidatedEvent;
import com.micro.demo_order.saga.events.ReserveInventoryEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class    OrderEventListener {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public OrderEventListener(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //order service act as orchestrator
//    @KafkaListener(topics = "order-created", groupId = "order", containerFactory = "kafkaOrderListenerContainerFactory")
//    public void handleOrderCreated(OrderCreatedEvent event) {
//        System.out.println("Received Order Created Event in Order Service: " + event);
//        String payload = null;
//        try {
//            payload = objectMapper.writeValueAsString(new ValidatePaymentCommand(event.getOrderId()));
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        kafkaTemplate.send("validate-payment", payload);
//    }

    //continue orchestration
//    @KafkaListener(
//            topics = "payment-validated",
//            groupId = "payment-validated-group",
//            containerFactory = "paymentValidatedKafkaListenerContainerFactory"
//    )
//    public void handlePaymentValidated(PaymentValidatedEvent event) {
//
//        String payload = null;
//        try {
//            payload = objectMapper.writeValueAsString(new ReserveInventoryCommand(event.getOrderId()));
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("Received Payment Validated Event in Order Service: " + event);
//    }
}

