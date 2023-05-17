package com.bcc.food_order_system.controller;

import com.bcc.food_order_system.contracts.restaurant.AddRestaurantRequest;
import com.bcc.food_order_system.service.RestaurantService;
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
@RequestMapping("/restaurant")
@Slf4j
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/add")
    public CompletionStage<ResponseEntity<Void>> add(@Valid @RequestBody AddRestaurantRequest addRestaurantRequest) {
        log.info("Received request to add restaurant: " + addRestaurantRequest.toString());
        return restaurantService.add(addRestaurantRequest)
                .thenApply(ResponseEntity::ok);
    }
}
