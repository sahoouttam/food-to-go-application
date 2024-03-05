package com.food.to.go.application.kitchenservice.api.web;

import java.time.LocalDateTime;

public class TicketAcceptance {

    private LocalDateTime readyBy;

    public TicketAcceptance() {
    }

    public TicketAcceptance(LocalDateTime readyBy) {
        this.readyBy = readyBy;
    }

    public LocalDateTime getReadyBy() {
        return readyBy;
    }

    public void setReadyBy(LocalDateTime readyBy) {
        this.readyBy = readyBy;
    }
}
