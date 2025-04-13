package com.micro.demo_order.saga.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentValidatedEvent {
    private String orderId;
    private String status; // "VALIDATED", "FAILED", etc.
    private String message;
}
