package com.food.to.go.application.kitchenservice.domain;

import org.springframework.beans.factory.annotation.Autowired;

public class KitchenService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TicketDomainEventPublisher ticketDomainEventPublisher;
    @Autowired
    private RestaurantRepository restaurantRepository;

    public void createMenu(long id, RestaurantMenu restaurantMenu) {
        Restaurant restaurant = new Restaurant(id, restaurantMenu.getMenuItems());
        restaurantRepository.save(restaurant);
    }

    public void reviseMenu(long ticketId, RestaurantMenu revisedMenu) {
        Restaurant restaurant = restaurantRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException(ticketId));
        restaurant.reviseMenu(revisedMenu);
    }
}
