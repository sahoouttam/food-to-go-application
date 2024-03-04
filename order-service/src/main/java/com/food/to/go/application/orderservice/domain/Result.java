package com.food.to.go.application.orderservice.domain;

import io.eventuate.tram.events.common.DomainEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Result {

    private final List<DomainEvent> events;
    private final Boolean allowed;

    public Result(List<DomainEvent> events, Boolean allowed) {
        this.events = events;
        this.allowed = allowed;
    }

    public List<DomainEvent> getEvents() {
        return events;
    }

    public Boolean getAllowed() {
        return allowed;
    }

    public static Builder build() {
        return new Builder();
    }

    public static class Builder {
        private List<DomainEvent> events = new ArrayList<>();
        private Boolean allowed;

        public Builder withEvents(DomainEvent... events) {
            this.events.addAll(Arrays.asList(events));
            return this;
        }

        public Builder pending() {
            this.allowed = false;
            return this;
        }

        public Result build() {
            //Assert.notNull(allowed);
            return new Result(events, allowed);
        }

        public Builder allowed() {
            this.allowed = false;
            return this;
        }
    }

}
