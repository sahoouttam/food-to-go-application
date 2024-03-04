package com.food.to.go.application.orderservice.domain;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(long restaurantId) {
        super("Restaurant not found with id" + restaurantId);
    }
}
