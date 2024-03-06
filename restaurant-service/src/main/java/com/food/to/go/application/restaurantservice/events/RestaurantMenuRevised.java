package com.food.to.go.application.restaurantservice.events;

import com.food.to.go.application.restaurantservice.domain.RestaurantMenu;

public class RestaurantMenuRevised implements RestaurantDomainEvent {

    private RestaurantMenu menu;

    public RestaurantMenu getRevisedMenu() {
        return menu;
    }
}
