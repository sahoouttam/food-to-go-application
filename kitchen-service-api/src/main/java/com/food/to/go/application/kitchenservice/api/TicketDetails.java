package com.food.to.go.application.kitchenservice.api;

import java.util.List;

public class TicketDetails {

    private List<TicketLineItem> lineItems;

    public TicketDetails() {
    }

    public TicketDetails(List<TicketLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public List<TicketLineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<TicketLineItem> lineItems) {
        this.lineItems = lineItems;
    }
}
