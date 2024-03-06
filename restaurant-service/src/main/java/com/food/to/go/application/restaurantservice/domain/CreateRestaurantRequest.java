package com.food.to.go.application.restaurantservice.domain;

import com.food.to.go.application.common.Address;

public class CreateRestaurantRequest {

    private String name;
    private Address address;
    private RestaurantMenu menu;

    public CreateRestaurantRequest() {
    }

    public CreateRestaurantRequest(String name, Address address, RestaurantMenu menu) {
        this.name = name;
        this.address = address;
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public RestaurantMenu getMenu() {
        return menu;
    }

    public void setMenu(RestaurantMenu menu) {
        this.menu = menu;
    }
}
