package com.food.to.go.application.orderservice.domain;

import com.food.to.go.application.common.valueobject.Money;
import com.food.to.go.application.common.valueobject.OrderState;
import com.food.to.go.application.api.events.OrderLineItem;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    private Long consumerId;
    private Long restaurantId;

    private OrderLineItems orderLineItems;

    private DeliveryInformation deliveryInformation;

    private PaymentInformation paymentInformation;

    private Money orderMinimum = new Money(Integer.MAX_VALUE);

    public Order() {
    }

    public Order(long consumerId, long restaurantId, List<OrderLineItem> orderLineItems) {
        this.consumerId = consumerId;
        this.restaurantId = restaurantId;
        this.orderLineItems = new OrderLineItems(orderLineItems);
        this.orderState = OrderState.APPROVAL_PENDING;
    }
}
