package com.food.to.go.application.orderservice.domain;

import com.food.to.go.application.api.events.OrderDomainEvent;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "order_service_restaurants")
@Access(AccessType.FIELD)
public class Restaurant {

    @Id
    private Long id;

    @ElementCollection
    @CollectionTable(name = "order_service_restaurant_menu_items")
    private List<MenuItem> menuItems;

    private String name;

    public Restaurant() {
    }

    public Restaurant(Long id, List<MenuItem> menuItems, String name) {
        this.id = id;
        this.menuItems = menuItems;
        this.name = name;
    }

    public List<OrderDomainEvent> reviseMenu(List<MenuItem> revisedMenu) {
        throw new UnsupportedOperationException();
    }

    /*public void verifyRestaurantDetails(TicketDetails ticketDetails) {

    }*/

    public Optional<MenuItem> findMenuItem(String menuItemId) {
        return menuItems.stream()
                .filter(menuItem -> menuItem.getId().equals(menuItemId))
                .findFirst();
    }

    public Long getId() {
        return id;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public String getName() {
        return name;
    }
}
