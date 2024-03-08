package com.food.to.go.application.deliveryservice.domain;

import com.food.to.go.application.common.Address;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Restaurant {

    @Id
    private Long id;

    private String restaurantName;
    private Address address;

    public Restaurant(Long restaurantId, String restaurantName, Address address) {
        this.id = restaurantId;
        this.restaurantName = restaurantName;
        this.address = address;
    }

    public static Restaurant create(Long restaurantId, String restaurantName, Address address) {
        return new Restaurant(restaurantId, restaurantName, address);
    }

    public Address getAddress() {
        return address;
    }
}
