package com.bcc.food_order_system.repository;

import com.bcc.food_order_system.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
