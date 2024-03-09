package com.food.to.go.application.accountingservice.domain;

import com.food.to.go.application.common.Money;
import io.eventuate.Command;

public class AuthorizeCommandInternal implements AccountCommand, Command {

    private String consumerId;
    private String orderId;
    private Money orderTotal;

    public AuthorizeCommandInternal() {
    }

    public AuthorizeCommandInternal(String consumerId, String orderId, Money orderTotal) {
        this.consumerId = consumerId;
        this.orderId = orderId;
        this.orderTotal = orderTotal;
    }

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Money getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Money orderTotal) {
        this.orderTotal = orderTotal;
    }
}
