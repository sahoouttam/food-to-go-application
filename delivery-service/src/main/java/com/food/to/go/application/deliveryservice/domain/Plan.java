package com.food.to.go.application.deliveryservice.domain;

import javax.persistence.ElementCollection;
import java.util.List;
import java.util.stream.Collectors;

public class Plan {

    @ElementCollection
    private List<Action> actions;

    public void add(Action action) {
        actions.add(action);
    }

    public void removeDelivery(long deliveryId) {
        actions = actions.stream()
                .filter(action -> !action.actionFor(deliveryId))
                .collect(Collectors.toList());
    }

    public List<Action> actionsForDelivery(long deliveryId) {
        return actions.stream()
                .filter(action -> action.actionFor(deliveryId))
                .collect(Collectors.toList());
    }

    public List<Action> getActions() {
        return actions;
    }
}
