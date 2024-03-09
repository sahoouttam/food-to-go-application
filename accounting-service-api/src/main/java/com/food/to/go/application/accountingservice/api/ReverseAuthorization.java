package com.food.to.go.application.accountingservice.api;

import com.food.to.go.application.common.Money;
import io.eventuate.tram.commands.common.Command;

public class ReverseAuthorization implements Command {

    private long consumerId;
    private long orderId;
    private Money orderTotal;

    public ReverseAuthorization() {
    }

    public ReverseAuthorization(long consumerId, long orderId, Money orderTotal) {
        this.consumerId = consumerId;
        this.orderId = orderId;
        this.orderTotal = orderTotal;
    }

    public long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(long consumerId) {
        this.consumerId = consumerId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Money getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Money orderTotal) {
        this.orderTotal = orderTotal;
    }
}
