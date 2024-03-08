package com.food.to.go.application.deliveryservice.domain;

import com.food.to.go.application.common.Address;
import com.food.to.go.application.deliveryservice.api.web.DeliveryActionType;

import java.time.LocalDateTime;

public class Action {

    private DeliveryActionType type;
    private long deliveryId;
    private Address address;
    private LocalDateTime time;

    public Action() {
    }

    public Action(DeliveryActionType type, long deliveryId, Address address, LocalDateTime time) {
        this.type = type;
        this.deliveryId = deliveryId;
        this.address = address;
        this.time = time;
    }

    public boolean actionFor(long deliveryId) {
        return this.deliveryId == deliveryId;
    }

    public static Action makePickUp(long deliveryId, Address pickupAddress, LocalDateTime pickupTime) {
        return new Action(DeliveryActionType.PICKUP, deliveryId, pickupAddress, pickupTime);
    }

    public static Action makeDropOff(long deliveryId, Address deliveryAddress, LocalDateTime deliveryTime) {
        return new Action(DeliveryActionType.DROPOFF, deliveryId, deliveryAddress, deliveryTime);
    }

    public DeliveryActionType getType() {
        return type;
    }

    public long getDeliveryId() {
        return deliveryId;
    }

    public Address getAddress() {
        return address;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
