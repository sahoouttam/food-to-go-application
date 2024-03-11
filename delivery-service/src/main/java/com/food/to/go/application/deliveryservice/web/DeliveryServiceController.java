package com.food.to.go.application.deliveryservice.web;

import com.food.to.go.application.deliveryservice.api.web.CourierAvailability;
import com.food.to.go.application.deliveryservice.api.web.DeliveryStatus;
import com.food.to.go.application.deliveryservice.domain.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeliveryServiceController {

    private DeliveryService deliveryService;

    public DeliveryServiceController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @RequestMapping(path = "/couriers/{courierId}/availability", method = RequestMethod.POST)
    public void updateCourierLocation(@PathVariable long courierId, @RequestBody CourierAvailability availability) {
        deliveryService.updateAvailability(courierId, availability.isAvailable());
    }

    @RequestMapping(path = "/deliveries/{deliveryId}", method = RequestMethod.GET)
    public ResponseEntity<DeliveryStatus> getDeliveryStatus(@PathVariable long deliveryId) {
        return deliveryService.getDeliveryInfo(deliveryId)
                .map(deliveryStatus -> new ResponseEntity<>(deliveryStatus, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
