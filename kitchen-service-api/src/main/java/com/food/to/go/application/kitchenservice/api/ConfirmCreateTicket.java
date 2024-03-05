package com.food.to.go.application.kitchenservice.api;

import io.eventuate.tram.commands.common.Command;

public class ConfirmCreateTicket implements Command {

    private Long ticketId;

    public ConfirmCreateTicket() {
    }

    public ConfirmCreateTicket(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }
}
