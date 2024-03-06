package com.food.to.go.application.restaurantservice.domain;

import javax.persistence.*;

@Entity
@Table(name = "restaurants")
@Access(AccessType.FIELD)
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private RestaurantMenu menu;

    public Restaurant() {
    }

    public Restaurant(String name, RestaurantMenu menu) {
        this.name = name;
        this.menu = menu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RestaurantMenu getMenu() {
        return menu;
    }

    public void setMenu(RestaurantMenu menu) {
        this.menu = menu;
    }
}
