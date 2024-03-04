package com.food.to.go.application.api.events;

import com.food.to.go.application.common.Address;

public class OrderCreatedEvent implements OrderDomainEvent {

    private OrderDetails orderDetails;
    private Address deliveryAddress;
    private String restaurantName;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(OrderDetails orderDetails, Address deliveryAddress, String restaurantName) {
        this.orderDetails = orderDetails;
        this.deliveryAddress = deliveryAddress;
        this.restaurantName = restaurantName;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
