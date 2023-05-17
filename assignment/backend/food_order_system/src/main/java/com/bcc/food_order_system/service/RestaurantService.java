package com.bcc.food_order_system.service;

import com.bcc.food_order_system.contracts.restaurant.AddRestaurantRequest;
import com.bcc.food_order_system.entity.Menu;
import com.bcc.food_order_system.entity.Restaurant;
import com.bcc.food_order_system.repository.ResturantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RestaurantService {
    private final ResturantRepository resturantRepository;
    private final MenuService menuService;

    @Autowired
    public RestaurantService(ResturantRepository resturantRepository,
                             MenuService menuService) {
        this.resturantRepository = resturantRepository;
        this.menuService = menuService;
    }

    public CompletionStage<Void> add(AddRestaurantRequest addRestaurantRequest) {
        log.info("Adding Restaurant...");
        return CompletableFuture.completedFuture(getRestaurant(addRestaurantRequest))
                .thenApply(resturantRepository::save)
                .thenApply(restaurant -> getMenus(addRestaurantRequest.getMenu(), restaurant))
                .thenAccept(menuService::addMenu);
    }

    private Restaurant getRestaurant(AddRestaurantRequest addRestaurantRequest) {
        return Restaurant.builder()
                .name(addRestaurantRequest.getName())
                .build();
    }

    private List<Menu> getMenus(List<Menu> menus, Restaurant restaurant) {
        return menus.stream()
                .map(menu -> {
                    menu.setRestaurant(restaurant);
                    return menu;
                })
                .collect(Collectors.toList());
    }
}
