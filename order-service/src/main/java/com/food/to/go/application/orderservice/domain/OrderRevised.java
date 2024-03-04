package com.food.to.go.application.orderservice.domain;

import com.food.to.go.application.api.events.OrderDomainEvent;
import com.food.to.go.application.common.Money;

public class OrderRevised implements OrderDomainEvent {

    private final OrderRevision orderRevision;
    private final Money currentTotalOrder;
    private final Money newTotalOrder;

    public OrderRevised(OrderRevision orderRevision, Money currentTotalOrder, Money newTotalOrder) {
        this.orderRevision = orderRevision;
        this.currentTotalOrder = currentTotalOrder;
        this.newTotalOrder = newTotalOrder;
    }

    public OrderRevision getOrderRevision() {
        return orderRevision;
    }

    public Money getCurrentTotalOrder() {
        return currentTotalOrder;
    }

    public Money getNewTotalOrder() {
        return newTotalOrder;
    }
}
