package com.food.to.go.application.orderservice.domain;

import com.food.to.go.application.common.Address;

import javax.persistence.Access;
import javax.persistence.AccessType;
import java.time.LocalDateTime;
import java.util.Objects;

@Access(AccessType.FIELD)
public class DeliveryInformation {

    private LocalDateTime deliveryTime;
    private Address deliveryAddress;

    public DeliveryInformation(LocalDateTime deliveryTime, Address deliveryAddress) {
        this.deliveryTime = deliveryTime;
        this.deliveryAddress = deliveryAddress;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryInformation that = (DeliveryInformation) o;
        return Objects.equals(deliveryTime, that.deliveryTime) && Objects.equals(deliveryAddress, that.deliveryAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deliveryTime, deliveryAddress);
    }
}
