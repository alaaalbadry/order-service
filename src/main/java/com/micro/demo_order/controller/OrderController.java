package com.micro.demo_order.controller;


import com.micro.demo_order.dto.OrderRequest;
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

    @GetMapping("/user/details")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public String getOrderDetails() {
        return "Order Details for Authenticated User";
    }

    @GetMapping("/admin/details")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getOrderDetailsForAdmin() {
        return "Order Details for Authenticated Admin";
    }

    @GetMapping("list")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Order>> getOrders() {
         List<Order> orders = orderService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);

    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }


    @PostMapping("/place")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return ResponseEntity.ok("Order created successfully");
    }

}
