package com.food.to.go.application.deliveryservice.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Courier {

    @Id
    private long id;

    private Plan plan;

    private boolean available;

    public Courier() {
    }

    public Courier(long courierId) {
        this.id = courierId;
        this.plan = new Plan();
    }

    public static Courier create(long courierId) {
        return new Courier(courierId);
    }

    public void noteAvailable() {
        this.available = true;
    }

    public void noteUnavailable() {
        this.available = false;
    }

    public List<Action> actionsForDelivery(long deliveryId) {
        return plan.actionsForDelivery(deliveryId);
    }

    public void addAction(Action action) {
        plan.add(action);
    }

    public void cancelDelivery(long deliveryId) {
        plan.removeDelivery(deliveryId);
    }

    public long getId() {
        return id;
    }

    public Plan getPlan() {
        return plan;
    }

    public boolean isAvailable() {
        return available;
    }
}

