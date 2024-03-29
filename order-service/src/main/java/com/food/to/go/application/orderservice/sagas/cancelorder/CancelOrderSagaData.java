package com.food.to.go.application.orderservice.sagas.cancelorder;

import com.food.to.go.application.common.Money;

public class CancelOrderSagaData {

    private Long orderId;
    private String reverseRequestId;
    private long restaurantId;
    private long consumerId;
    private Money orderTotal;

    public CancelOrderSagaData() {
    }

    public CancelOrderSagaData(long consumerId, long orderId, Money orderTotal) {
        this.consumerId = consumerId;
        this.orderId = orderId;
        this.orderTotal = orderTotal;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getReverseRequestId() {
        return reverseRequestId;
    }

    public void setReverseRequestId(String reverseRequestId) {
        this.reverseRequestId = reverseRequestId;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public long getConsumerId() {
        return consumerId;
    }

    public Money getOrderTotal() {
        return orderTotal;
    }

}
