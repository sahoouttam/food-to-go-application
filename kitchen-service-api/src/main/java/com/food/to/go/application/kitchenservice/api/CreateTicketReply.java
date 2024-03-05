package com.food.to.go.application.kitchenservice.api;

public class CreateTicketReply {

    private long ticketId;

    public CreateTicketReply() {
    }

    public CreateTicketReply(long ticketId) {
        this.ticketId = ticketId;
    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }
}
