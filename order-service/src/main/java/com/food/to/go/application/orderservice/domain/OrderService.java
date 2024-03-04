package com.food.to.go.application.orderservice.domain;

import com.food.to.go.application.orderservice.sagas.cancelorder.CancelOrderSaga;
import com.food.to.go.application.orderservice.sagas.createorder.CreateOrderSaga;
import com.food.to.go.application.orderservice.sagas.reviseorder.ReviseOrderSaga;
import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final SagaInstanceFactory sagaInstanceFactory;
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final CreateOrderSaga createOrderSaga;
    private final CancelOrderSaga cancelOrderSaga;
    private final ReviseOrderSaga reviseOrderSaga;
    private final OrderDomainEventPublisher orderAggregateEventPublisher;

    public OrderService(SagaInstanceFactory sagaInstanceFactory,
                        OrderRepository orderRepository,
                        RestaurantRepository restaurantRepository,
                        CreateOrderSaga createOrderSaga,
                        CancelOrderSaga cancelOrderSaga,
                        ReviseOrderSaga reviseOrderSaga,
                        OrderDomainEventPublisher orderAggregateEventPublisher) {
        this.sagaInstanceFactory = sagaInstanceFactory;
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
        this.createOrderSaga = createOrderSaga;
        this.cancelOrderSaga = cancelOrderSaga;
        this.reviseOrderSaga = reviseOrderSaga;
        this.orderAggregateEventPublisher = orderAggregateEventPublisher;
    }
}
