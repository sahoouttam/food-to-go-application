package com.food.to.go.application.orderservice.domain;

public class RevisedOrder {

    private final Order order;
    private final LineItemQuantityChange lineItemQuantityChange;

    public RevisedOrder(Order order, LineItemQuantityChange lineItemQuantityChange) {
        this.order = order;
        this.lineItemQuantityChange = lineItemQuantityChange;
    }

    public Order getOrder() {
        return order;
    }

    public LineItemQuantityChange getLineItemQuantityChange() {
        return lineItemQuantityChange;
    }
}
