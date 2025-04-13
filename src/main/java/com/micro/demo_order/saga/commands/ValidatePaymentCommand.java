package com.micro.demo_order.saga.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidatePaymentCommand {
    private String orderId;
    private String userId;
    private String paymentMethod;
    private Double amount;

    public ValidatePaymentCommand(String orderId) {
        this.orderId = orderId;
    }
}
