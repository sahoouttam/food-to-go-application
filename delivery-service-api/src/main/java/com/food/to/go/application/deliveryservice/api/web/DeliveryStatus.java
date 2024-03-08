package com.food.to.go.application.deliveryservice.api.web;

import java.util.List;

public class DeliveryStatus {

    private DeliveryInfo deliveryInfo;
    private long assignedCourier;
    private List<ActionInfo> courierActions;

    public DeliveryStatus() {
    }

    public DeliveryStatus(DeliveryInfo deliveryInfo, long assignedCourier, List<ActionInfo> courierActions) {
        this.deliveryInfo = deliveryInfo;
        this.assignedCourier = assignedCourier;
        this.courierActions = courierActions;
    }

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    public long getAssignedCourier() {
        return assignedCourier;
    }

    public void setAssignedCourier(long assignedCourier) {
        this.assignedCourier = assignedCourier;
    }

    public List<ActionInfo> getCourierActions() {
        return courierActions;
    }

    public void setCourierActions(List<ActionInfo> courierActions) {
        this.courierActions = courierActions;
    }
}
