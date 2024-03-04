package com.food.to.go.application.orderservice.domain;

import com.food.to.go.application.api.events.*;
import com.food.to.go.application.common.UnsupportedStateTransitionException;
import com.food.to.go.application.common.Money;
import com.food.to.go.application.common.OrderState;
import io.eventuate.tram.events.aggregates.ResultWithDomainEvents;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

import static com.food.to.go.application.common.OrderState.*;

@Entity
@Table(name = "orders")
public class Order {



    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    private Long consumerId;
    private Long restaurantId;

    private OrderLineItems orderLineItems;

    private DeliveryInformation deliveryInformation;

    private PaymentInformation paymentInformation;

    private Money orderMinimum = new Money(Integer.MAX_VALUE);

    public Order() {
    }

    public Order(long consumerId, long restaurantId, DeliveryInformation deliveryInformation, List<OrderLineItem> orderLineItems) {
        this.consumerId = consumerId;
        this.restaurantId = restaurantId;
        this.deliveryInformation = deliveryInformation;
        this.orderLineItems = new OrderLineItems(orderLineItems);
        this.state = APPROVAL_PENDING;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeliveryInformation getDeliveryInformation() {
        return deliveryInformation;
    }

    public Money getOrderTotal() {
        return orderLineItems.orderTotal();
    }

    public static ResultWithDomainEvents<Order, OrderDomainEvent> createOrder(long consumerId,
                                                                              Restaurant restaurant,
                                                                              DeliveryInformation deliveryInformation,
                                                                              List<OrderLineItem> orderLineItems) {
        Order order = new Order(consumerId, restaurant.getId(), deliveryInformation, orderLineItems);
        List<OrderDomainEvent> events = Collections.singletonList(
                new OrderCreatedEvent(
                        new OrderDetails(consumerId, restaurant.getId(), orderLineItems, order.getOrderTotal()),
                        deliveryInformation.getDeliveryAddress(), restaurant.getName()));
        return new ResultWithDomainEvents<>(order, events);
    }

    public List<OrderDomainEvent> cancel() {
        switch (state) {
            case APPROVED:
                this.state = CANCEL_PENDING;
                return Collections.emptyList();
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public List<OrderDomainEvent> undoPendingCancel() {
        switch (state) {
            case CANCEL_PENDING:
                this.state = APPROVED;
                return Collections.emptyList();
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public List<OrderDomainEvent> noteCancelled() {
        switch (state) {
            case CANCEL_PENDING:
                this.state = CANCELLED;
                return Collections.singletonList(new OrderCancelled());
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public List<OrderDomainEvent> noteApproved() {
        switch (state) {
            case APPROVAL_PENDING:
                this.state = APPROVED;
                return Collections.singletonList(new OrderAuthorized());
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public List<OrderDomainEvent> noteRejected() {
        switch (state) {
            case APPROVAL_PENDING:
                this.state = REJECTED;
                return Collections.singletonList(new OrderRejected());
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public List<OrderDomainEvent> noteReversingAuthorization() {
        return null;
    }

    public ResultWithDomainEvents<LineItemQuantityChange, OrderDomainEvent> revise(OrderRevision orderRevision) {
        switch (state) {
            case APPROVED:
                LineItemQuantityChange lineItemQuantityChange = orderLineItems.lineItemQuantityChange(orderRevision);
                if (lineItemQuantityChange.getNewOrderTotal().isGreaterThanOrEqual(orderMinimum)) {
                    throw new OrderMinimumNotMetException();
                }
                this.state = REVISION_PENDING;
                return new ResultWithDomainEvents<>(lineItemQuantityChange,
                        new OrderRevisionProposed(orderRevision, lineItemQuantityChange.getCurrentOrderTotal(), lineItemQuantityChange.getNewOrderTotal()));
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public List<OrderDomainEvent> rejectRevision() {
        switch (state) {
            case REVISION_PENDING:
                this.state = APPROVED;
                return Collections.emptyList();
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public List<OrderDomainEvent> confirmRevision(OrderRevision orderRevision) {
        switch (state) {
            case REVISION_PENDING:
                LineItemQuantityChange lineItemQuantityChange = orderLineItems.lineItemQuantityChange(orderRevision);
                orderRevision
                        .getDeliveryInformation()
                        .ifPresent(newDeliveryInformation -> this.deliveryInformation = getDeliveryInformation());
                if (orderRevision.getRevisedOrderLineItems() != null && orderRevision.getRevisedOrderLineItems().size() > 0) {
                    orderLineItems.updateLineItems(orderRevision);
                }
                this.state = APPROVED;
                return Collections.singletonList(new OrderRevised(orderRevision, lineItemQuantityChange.getCurrentOrderTotal(), lineItemQuantityChange.getNewOrderTotal()));
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public Long getVersion() {
        return version;
    }

    public List<OrderLineItem> getLineItems() {
        return orderLineItems.getLineItems();
    }

    public OrderState getState() {
        return state;
    }

    public long getRestaurantId() {
        return restaurantId;
    }


    public Long getConsumerId() {
        return consumerId;
    }

}
