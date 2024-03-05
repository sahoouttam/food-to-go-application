package com.food.to.go.application.kitchenservice.api;

import io.eventuate.tram.commands.common.Command;

public class ChangeTicketLineItemQuantity implements Command {

    public ChangeTicketLineItemQuantity(long orderId) {
    }
}
