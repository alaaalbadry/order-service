package com.micro.demo_order.controller;


import com.micro.demo_order.model.Order;
import com.micro.demo_order.model.OrderRepository;
import com.micro.demo_order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/details")
    public String getOrderDetails() {
        return "Order Details for Authenticated User";
    }
    @GetMapping("/user/my-orders")
    @PreAuthorize("hasRole('USER')")
    public String getUserOrders() {
        return "List of user's orders";
    }

    @GetMapping("/admin/all-orders")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAllOrders() {
        return "List of all orders (Admin Only)";
    }

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
