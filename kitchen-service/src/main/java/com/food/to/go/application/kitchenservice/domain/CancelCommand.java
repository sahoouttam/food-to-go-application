package com.food.to.go.application.kitchenservice.domain;

public class CancelCommand {

    private long orderId;
    private boolean force;

    public long getOrderId() {
        return orderId;
    }

    public boolean isForce() {
        return force;
    }
}
