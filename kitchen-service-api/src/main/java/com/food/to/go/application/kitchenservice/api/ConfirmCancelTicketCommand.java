package com.food.to.go.application.kitchenservice.api;

import io.eventuate.tram.commands.common.Command;

public class ConfirmCancelTicketCommand implements Command {

    private long restaurantId;
    private long orderId;

    public ConfirmCancelTicketCommand() {
    }

    public ConfirmCancelTicketCommand(long restaurantId, long orderId) {
        this.restaurantId = restaurantId;
        this.orderId = orderId;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
