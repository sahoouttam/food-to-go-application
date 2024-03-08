package com.food.to.go.application.deliveryservice.domain;

import com.food.to.go.application.common.Address;
import com.food.to.go.application.deliveryservice.api.web.DeliveryState;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Delivery {

    @Id
    private long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="street1", column = @Column(name="pickup_street1")),
            @AttributeOverride(name="street2", column = @Column(name="pickup_street2")),
            @AttributeOverride(name="city", column = @Column(name="pickup_city")),
            @AttributeOverride(name="state", column = @Column(name="pickup_state")),
            @AttributeOverride(name="zip", column = @Column(name="pickup_zip")),
    })
    private Address pickupAddress;

    @Enumerated(EnumType.STRING)
    private DeliveryState state;

    private long restaurantId;
    private LocalDateTime pickUpTime;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="street1", column = @Column(name="pickup_street1")),
            @AttributeOverride(name="street2", column = @Column(name="pickup_street2")),
            @AttributeOverride(name="city", column = @Column(name="pickup_city")),
            @AttributeOverride(name="state", column = @Column(name="pickup_state")),
            @AttributeOverride(name="zip", column = @Column(name="pickup_zip")),
    })
    private Address deliveryAddress;
    private LocalDateTime deliveryTime;

    private Long assignedCourier;
    private LocalDateTime readyBy;

    public Delivery() {
    }

    public Delivery(long orderId, long restaurantId, Address pickupAddress, Address deliveryAddress) {
        this.id = orderId;
        this.pickupAddress = pickupAddress;
        this.deliveryAddress = deliveryAddress;
        this.restaurantId = restaurantId;
        this.state = DeliveryState.PENDING;
    }

    public static Delivery create(long orderId, long restaurantId, Address pickupAddress, Address deliveryAddress) {
        return new Delivery(orderId, restaurantId, pickupAddress, deliveryAddress);
    }

    public void schedule(LocalDateTime readyBy, long assignedCourier) {
        this.readyBy = readyBy;
        this.assignedCourier = assignedCourier;
        this.state = DeliveryState.SCHEDULED;
    }

    public void cancel() {
        this.state = DeliveryState.CANCELLED;
        this.assignedCourier = null;
    }

    public long getId() {
        return id;
    }

    public Address getPickupAddress() {
        return pickupAddress;
    }

    public DeliveryState getState() {
        return state;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public LocalDateTime getPickUpTime() {
        return pickUpTime;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public Long getAssignedCourier() {
        return assignedCourier;
    }

    public LocalDateTime getReadyBy() {
        return readyBy;
    }
}
