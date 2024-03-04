package com.food.to.go.application.orderservice.domain;

import com.food.to.go.application.common.OrderState;
import io.eventuate.tram.events.common.DomainEvent;

public class OrderCancelRequested implements DomainEvent {

    private final OrderState state;

    public OrderCancelRequested(OrderState state) {
        this.state = state;
    }

    public OrderState getState() {
        return state;
    }
}
