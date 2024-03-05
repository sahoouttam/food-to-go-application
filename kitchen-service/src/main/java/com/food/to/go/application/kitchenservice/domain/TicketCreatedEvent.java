package com.food.to.go.application.kitchenservice.domain;

import com.food.to.go.application.kitchenservice.api.TicketDetails;
import com.food.to.go.application.kitchenservice.api.events.TicketDomainEvent;

public class TicketCreatedEvent implements TicketDomainEvent {

    public TicketCreatedEvent(long id, TicketDetails details) {
    }
}
