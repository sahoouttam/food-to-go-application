package com.food.to.go.application.kitchenservice.api;

import io.eventuate.tram.commands.CommandDestination;
import io.eventuate.tram.commands.common.Command;

@CommandDestination("restaurantService")
public class CreateTicket implements Command {

    private long orderId;
    private TicketDetails ticketDetails;
    private long restaurantId;

    public CreateTicket() {
    }

    public CreateTicket(long restaurantId, long orderId, TicketDetails ticketDetails) {
        this.restaurantId = restaurantId;
        this.orderId = orderId;
        this.ticketDetails = ticketDetails;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public TicketDetails getTicketDetails() {
        return ticketDetails;
    }

    public void setTicketDetails(TicketDetails ticketDetails) {
        this.ticketDetails = ticketDetails;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
