package com.food.to.go.application.orderservice.domain;

import java.util.Map;
import java.util.Optional;

public class OrderRevision {

    private Optional<DeliveryInformation> deliveryInformation;
    private Map<String, Integer> revisedLineItemQuantities;

    public OrderRevision() {
    }

    public OrderRevision(Optional<DeliveryInformation> deliveryInformation,
                         Map<String, Integer> revisedLineItemQuantities) {
        this.deliveryInformation = deliveryInformation;
        this.revisedLineItemQuantities = revisedLineItemQuantities;
    }

    public Optional<DeliveryInformation> getDeliveryInformation() {
        return deliveryInformation;
    }

    public void setDeliveryInformation(Optional<DeliveryInformation> deliveryInformation) {
        this.deliveryInformation = deliveryInformation;
    }

    public Map<String, Integer> getRevisedLineItemQuantities() {
        return revisedLineItemQuantities;
    }

    public void setRevisedLineItemQuantities(Map<String, Integer> revisedLineItemQuantities) {
        this.revisedLineItemQuantities = revisedLineItemQuantities;
    }
}
