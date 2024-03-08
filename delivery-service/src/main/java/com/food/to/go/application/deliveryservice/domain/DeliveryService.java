package com.food.to.go.application.deliveryservice.domain;

import com.food.to.go.application.common.Address;
import com.food.to.go.application.deliveryservice.api.web.ActionInfo;
import com.food.to.go.application.deliveryservice.api.web.DeliveryInfo;
import com.food.to.go.application.deliveryservice.api.web.DeliveryStatus;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class DeliveryService {

    private final RestaurantRepository restaurantRepository;
    private final DeliveryRepository deliveryRepository;
    private final CourierRepository courierRepository;
    private Random random = new Random();

    public DeliveryService(RestaurantRepository restaurantRepository,
                           DeliveryRepository deliveryRepository,
                           CourierRepository courierRepository) {
        this.restaurantRepository = restaurantRepository;
        this.deliveryRepository = deliveryRepository;
        this.courierRepository = courierRepository;
    }

    public void createRestaurant(long restaurantId, String restaurantName, Address address) {
        restaurantRepository.save(new Restaurant(restaurantId, restaurantName, address));
    }

    public void createDelivery(long orderId, long restaurantId, Address deliveryAddress) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        deliveryRepository.save(new Delivery(orderId, restaurantId, restaurant.getAddress(), deliveryAddress));
    }

    public void scheduleDelivery(long orderId, LocalDateTime readyBy) {
        Delivery delivery = deliveryRepository.findById(orderId).get();
        List<Courier> couriers = courierRepository.findAllAvailable();
        Courier courier = couriers.get(random.nextInt(couriers.size()));
        courier.addAction(Action.makePickUp(delivery.getId(), delivery.getPickupAddress(), readyBy));
        courier.addAction(Action.makeDropOff(delivery.getId(), delivery.getDeliveryAddress(), readyBy.plusMinutes(30)));
        delivery.schedule(readyBy, courier.getId());
    }

    public void cancelDelivery(long orderId) {
        Delivery delivery = deliveryRepository.findById(orderId).get();
        Long assignedCourierId = delivery.getAssignedCourier();
        delivery.cancel();
        if (assignedCourierId != null) {
            Courier courier = courierRepository.findById(assignedCourierId).get();
            courier.cancelDelivery(delivery.getId());
        }
    }

    public void noteAvailable(long courierId) {
        courierRepository.findOrCreateCourier(courierId).noteAvailable();
    }

    public void noteUnavailable(long courierId) {
        courierRepository.findOrCreateCourier(courierId).noteUnavailable();
    }

    private Courier findOrCreateCourier(long courierId) {
        Courier courier = Courier.create(courierId);
        try {
            return courierRepository.save(courier);
        } catch (DuplicateKeyException exception) {
            return courierRepository.findById(courierId).get();
        }
    }

    public void updateAvailability(long courierId, boolean available) {
        if (available) {
            noteAvailable(courierId);
        } else {
            noteUnavailable(courierId);
        }
    }

    @Transactional
    public Optional<DeliveryStatus> getDeliveryInfo(long deliveryId) {
        return deliveryRepository.findById(deliveryId).map(delivery -> {
            Long assignedCourier = delivery.getAssignedCourier();
            List<Action> courierActions = Collections.EMPTY_LIST;
            if (assignedCourier != null) {
                Courier courier = courierRepository.findById(assignedCourier).get();
                courierActions = courier.actionsForDelivery(deliveryId);
            }
            return makeDeliveryStatus(delivery, assignedCourier, courierActions);
        });
    }

    private DeliveryStatus makeDeliveryStatus(Delivery delivery, Long assignedCourier, List<Action> courierActions) {
        return new DeliveryStatus(makeDeliveryInfo(delivery), assignedCourier,
                courierActions.stream().map(this::makeActionInfo).collect(Collectors.toList()));
    }

    private DeliveryInfo makeDeliveryInfo(Delivery delivery) {
        return new DeliveryInfo(delivery.getId(), delivery.getState());
    }

    private ActionInfo makeActionInfo(Action action) {
        return new ActionInfo(action.getType());
    }
}
