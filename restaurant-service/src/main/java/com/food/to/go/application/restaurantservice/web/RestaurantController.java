package com.food.to.go.application.restaurantservice.web;

import com.food.to.go.application.restaurantservice.domain.CreateRestaurantRequest;
import com.food.to.go.application.restaurantservice.domain.Restaurant;
import com.food.to.go.application.restaurantservice.domain.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(method = RequestMethod.POST)
    public CreateRestaurantResponse create(@RequestBody CreateRestaurantRequest request) {
        Restaurant restaurant = restaurantService.create(request);
        return new CreateRestaurantResponse(restaurant.getId());
    }

    public ResponseEntity<GetRestaurantResponse> get(@PathVariable long restaurantId) {
        return restaurantService.findById(restaurantId)
                .map(restaurant -> new ResponseEntity<>(makeGetRestaurantResponse(restaurant), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    private GetRestaurantResponse makeGetRestaurantResponse(Restaurant restaurant) {
        return new GetRestaurantResponse(restaurant.getId(), restaurant.getName());
    }
}
