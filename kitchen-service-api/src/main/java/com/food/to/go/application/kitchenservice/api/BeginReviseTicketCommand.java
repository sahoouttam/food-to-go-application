package com.food.to.go.application.kitchenservice.api;

import com.food.to.go.application.common.RevisedOrderLineItem;
import io.eventuate.tram.commands.common.Command;

import java.util.List;

public class BeginReviseTicketCommand implements Command {

    private long restaurantId;
    private long orderId;
    private List<RevisedOrderLineItem> revisedOrderLineItems;

    public BeginReviseTicketCommand() {
    }

    public BeginReviseTicketCommand(long restaurantId, long orderId, List<RevisedOrderLineItem> revisedOrderLineItems) {
        this.restaurantId = restaurantId;
        this.orderId = orderId;
        this.revisedOrderLineItems = revisedOrderLineItems;
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

    public List<RevisedOrderLineItem> getRevisedOrderLineItems() {
        return revisedOrderLineItems;
    }

    public void setRevisedOrderLineItems(List<RevisedOrderLineItem> revisedOrderLineItems) {
        this.revisedOrderLineItems = revisedOrderLineItems;
    }
}
