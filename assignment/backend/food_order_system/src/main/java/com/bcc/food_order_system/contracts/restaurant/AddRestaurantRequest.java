package com.bcc.food_order_system.contracts.restaurant;

import com.bcc.food_order_system.entity.Menu;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class AddRestaurantRequest {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private List<Menu> menu;
}
