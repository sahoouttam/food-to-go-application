package com.food.to.go.application.consumerservice.domain;

import com.food.to.go.application.common.Money;
import com.food.to.go.application.common.PersonName;
import io.eventuate.tram.events.publisher.ResultWithEvents;

import javax.persistence.*;

@Entity
@Table(name = "consumers")
public class Consumer {

    @Id
    @GeneratedValue
    private Long id;

    private PersonName name;

    public Consumer() {
    }

    public Consumer(PersonName name) {
        this.name = name;
    }

    public void validateOrderByConsumer(Money orderTotal) {

    }

    public Long getId() {
        return id;
    }

    public PersonName getName() {
        return name;
    }

    public static ResultWithEvents<Consumer> create(PersonName name) {
        return new ResultWithEvents<>(new Consumer(name), new ConsumerCreated());
    }
}
