package com.bcc.food_order_system.controller;

import com.bcc.food_order_system.contracts.order.CreateOrderRequest;
import com.bcc.food_order_system.service.OrderService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public CompletionStage<ResponseEntity<Void>> createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        log.info("Received request to add orders: " + createOrderRequest.toString());
        return orderService.createOrder(createOrderRequest)
                .thenApply(ResponseEntity::ok);
    }
}
