package com.food.to.go.application.restaurantservice.domain;

import com.food.to.go.application.restaurantservice.events.RestaurantDomainEvent;
import io.eventuate.tram.events.aggregates.AbstractAggregateDomainEventPublisher;
import io.eventuate.tram.events.publisher.DomainEventPublisher;

import java.util.function.Function;

public class RestaurantDomainEventPublisher extends AbstractAggregateDomainEventPublisher<Restaurant, RestaurantDomainEvent> {

    public RestaurantDomainEventPublisher(DomainEventPublisher eventPublisher) {
        super(eventPublisher, Restaurant.class, Restaurant::getId);
    }
}
