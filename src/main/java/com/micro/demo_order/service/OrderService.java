package com.micro.demo_order.service;


import com.micro.demo_order.model.Order;
import com.micro.demo_order.model.OrderRepository;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Retryable(value = StaleObjectStateException.class, maxAttempts = 3)
    @Transactional
    public void updateOrder(Long OrderId, BigDecimal newAmount) {
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
        return orderRepository.save(Order);
    }
}