package com.food.to.go.application.kitchenservice.api;

import io.eventuate.tram.commands.common.Command;

public class CancelCreateTicket implements Command {

    private long ticketId;

    public CancelCreateTicket() {
    }

    public CancelCreateTicket(long ticketId) {
        this.ticketId = ticketId;
    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }
}
