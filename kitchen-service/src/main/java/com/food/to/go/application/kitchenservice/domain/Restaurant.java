package com.food.to.go.application.kitchenservice.domain;

import com.food.to.go.application.kitchenservice.api.TicketDetails;
import io.eventuate.tram.events.common.DomainEvent;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "kitchen_service_restaurants")
@Access(AccessType.FIELD)
public class Restaurant {

    @Id
    private Long id;

    @ElementCollection
    @CollectionTable(name = "kitchen_service_restaurant_menu_items")
    private List<MenuItem> menuItems;

    public Restaurant() {
    }

    public Restaurant(Long id, List<MenuItem> menuItems) {
        this.id = id;
        this.menuItems = menuItems;
    }

    public List<DomainEvent> reviseMenu(RestaurantMenu restaurantMenu) {
        throw new UnsupportedOperationException();
    }

    public void verifyRestaurantDetails(TicketDetails ticketDetails) {

    }

    public Long getId() {
        return id;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
}
