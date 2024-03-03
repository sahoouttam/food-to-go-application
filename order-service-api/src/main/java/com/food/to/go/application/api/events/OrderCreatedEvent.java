package com.food.to.go.application.api.events;

public class OrderCreatedEvent implements OrderDomainEvent {

    private OrderDetails orderDetails;
    private String restaurantName;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(OrderDetails orderDetails, String restaurantName) {
        this.orderDetails = orderDetails;
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
}
