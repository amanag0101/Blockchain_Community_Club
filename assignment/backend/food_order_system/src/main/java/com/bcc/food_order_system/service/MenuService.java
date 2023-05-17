package com.bcc.food_order_system.service;

import com.bcc.food_order_system.entity.Menu;
import com.bcc.food_order_system.repository.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
@Slf4j
public class MenuService {
    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public CompletionStage<List<Menu>> addMenu(List<Menu> menus) {
        log.info("Adding Menu...");
        return CompletableFuture.completedFuture(menuRepository.saveAll(menus));
    }
}
