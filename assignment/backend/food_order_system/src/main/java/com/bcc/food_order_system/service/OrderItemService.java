package com.bcc.food_order_system.service;

import com.bcc.food_order_system.entity.OrderItem;
import com.bcc.food_order_system.repository.OrderItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
@Slf4j
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public CompletionStage<List<OrderItem>> addOrderItems(List<OrderItem> orderItems) {
        log.info("Adding order items...");
        return CompletableFuture.completedFuture(orderItemRepository.saveAll(orderItems));
    }
}
