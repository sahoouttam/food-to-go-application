package com.food.to.go.application.orderservice.domain;

import com.food.to.go.application.api.events.OrderDomainEvent;
import io.eventuate.tram.events.aggregates.AbstractAggregateDomainEventPublisher;
import io.eventuate.tram.events.publisher.DomainEventPublisher;

public class OrderDomainEventPublisher extends AbstractAggregateDomainEventPublisher<Order, OrderDomainEvent> {
    protected OrderDomainEventPublisher(DomainEventPublisher eventPublisher) {
        super(eventPublisher, Order.class, Order::getId);
    }
}
