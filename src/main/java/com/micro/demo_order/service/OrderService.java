package com.micro.demo_order.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.demo_order.dto.OrderRequest;
import com.micro.demo_order.model.Order;
import com.micro.demo_order.model.OrderRepository;
import com.micro.demo_order.saga.OrderCreatedEvent;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;
    @Autowired private OrderEventPublisher orderEventPublisher;
    @Autowired
    private ObjectMapper objectMapper;
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Retryable(value = StaleObjectStateException.class, maxAttempts = 3)
    @Transactional
    public void updateOrder(Long OrderId, double newAmount) {
        Order Order = orderRepository.findById(OrderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Order.setAmount(newAmount);
        orderRepository.save(Order);
    }

    @Transactional
    public Order createOrder(Order Order) {
        if (Order.getId() != null) {
            throw new IllegalArgumentException("New Order cannot have an ID");
        }
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent("123","456",55.8);
        String message = null;
        try {
            message = objectMapper.writeValueAsString(orderCreatedEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        orderEventPublisher.publishOrderCreatedEvent(message);
        return orderRepository.save(Order);
    }

    public void placeOrder(OrderRequest request) {
        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setAmount(request.getAmount());
        order.setDate(LocalDateTime.now());
        order.setVersion(request.hashCode());
        order.setName("alaa1");

        orderRepository.save(order);
    }
}