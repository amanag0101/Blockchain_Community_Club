package com.bcc.food_order_system.contracts.order;

import com.bcc.food_order_system.entity.OrderItem;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CreateOrderRequest {
    private List<OrderItem> orderItems;
}
