package com.bcc.food_order_system.service;

import com.bcc.food_order_system.contracts.order.CreateOrderRequest;
import com.bcc.food_order_system.entity.Order;
import com.bcc.food_order_system.entity.OrderItem;
import com.bcc.food_order_system.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
    }

    public CompletionStage<Void> createOrder(CreateOrderRequest createOrderRequest) {
        log.info("Creating Order...");
        return CompletableFuture.completedFuture(getOrder())
                .thenApply(orderRepository::save)
                .thenAccept(order -> orderItemService.addOrderItems(getOrderItems(createOrderRequest.getOrderItems(), order)));
    }

    private Order getOrder() {
        return Order.builder()
                .createdAt(LocalDateTime.now())
                .orderItems(new ArrayList<>())
                .status("Preparing")
                .build();
    }

    private List<OrderItem> getOrderItems(List<OrderItem> orderItems, Order order) {
        return orderItems.stream()
                .map(orderItem -> {
                    orderItem.setOrder(order);
                    return orderItem;
                })
                .toList();
    }
}
