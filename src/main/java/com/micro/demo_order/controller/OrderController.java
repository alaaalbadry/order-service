package com.micro.demo_order.controller;


import com.micro.demo_order.model.Order;
import com.micro.demo_order.model.OrderRepository;
import com.micro.demo_order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("list")
    public ResponseEntity<List<Order>> getOrders() {
         List<Order> orders = orderService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);

    }

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

}
