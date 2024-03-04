package com.food.to.go.application.orderservice.domain;

import com.food.to.go.application.common.RevisedOrderLineItem;

import java.util.List;
import java.util.Optional;

public class OrderRevision {

    private Optional<DeliveryInformation> deliveryInformation;
    private List<RevisedOrderLineItem> revisedOrderLineItems;

    public OrderRevision() {
    }

    public OrderRevision(Optional<DeliveryInformation> deliveryInformation,
                         List<RevisedOrderLineItem> revisedOrderLineItems) {
        this.deliveryInformation = deliveryInformation;
        this.revisedOrderLineItems = revisedOrderLineItems;
    }

    public Optional<DeliveryInformation> getDeliveryInformation() {
        return deliveryInformation;
    }

    public void setDeliveryInformation(Optional<DeliveryInformation> deliveryInformation) {
        this.deliveryInformation = deliveryInformation;
    }

    public List<RevisedOrderLineItem> getRevisedOrderLineItems() {
        return revisedOrderLineItems;
    }

    public void setRevisedOrderLineItems(List<RevisedOrderLineItem> revisedOrderLineItems) {
        this.revisedOrderLineItems = revisedOrderLineItems;
    }
}
