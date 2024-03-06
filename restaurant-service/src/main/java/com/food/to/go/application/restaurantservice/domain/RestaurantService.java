package com.food.to.go.application.restaurantservice.domain;

import com.food.to.go.application.restaurantservice.events.RestaurantCreated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Transactional
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantDomainEventPublisher restaurantDomainEventPublisher;

    public Restaurant create(CreateRestaurantRequest request) {
        Restaurant restaurant = new Restaurant(request.getName(), request.getMenu());
        restaurantRepository.save(restaurant);
        restaurantDomainEventPublisher.publish(restaurant, Collections.singletonList(
                        new RestaurantCreated(restaurant.getName(), request.getAddress(), request.getMenu())));
        return restaurant;
    }

    public Optional<Restaurant> findById(long restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }
}
