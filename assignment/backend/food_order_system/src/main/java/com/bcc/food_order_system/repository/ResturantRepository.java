package com.bcc.food_order_system.repository;

import com.bcc.food_order_system.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResturantRepository extends JpaRepository<Restaurant, Long> {
}
