package com.food.to.go.application.consumerservice.domain;

import com.food.to.go.application.common.Money;
import com.food.to.go.application.common.PersonName;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.events.publisher.ResultWithEvents;

import java.util.Optional;

public class ConsumerService {

    private final ConsumerRepository consumerRepository;
    private final DomainEventPublisher domainEventPublisher;

    public ConsumerService(ConsumerRepository consumerRepository, DomainEventPublisher domainEventPublisher) {
        this.consumerRepository = consumerRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    public void validateOrderForConsumer(long consumerId, Money orderTotal) {
        Optional<Consumer> consumer = consumerRepository.findById(consumerId);
        consumer.orElseThrow(ConsumerNotFoundException::new)
                .validateOrderByConsumer(orderTotal);
    }

    public ResultWithEvents<Consumer> create(PersonName name) {
        ResultWithEvents<Consumer> resultWithEvents = Consumer.create(name);
        consumerRepository.save(resultWithEvents.result);
        domainEventPublisher.publish(Consumer.class, resultWithEvents.result.getId(), resultWithEvents.events);
        return resultWithEvents;
    }

    public Optional<Consumer> findById(long consumerId) {
        return consumerRepository.findById(consumerId);
    }
}
