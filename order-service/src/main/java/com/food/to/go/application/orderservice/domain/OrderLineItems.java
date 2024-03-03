package com.food.to.go.application.orderservice.domain;

import com.food.to.go.application.common.valueobject.Money;
import com.food.to.go.application.api.events.OrderLineItem;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class OrderLineItems {

    @ElementCollection
    @CollectionTable(name = "order_line_items")
    private List<OrderLineItem> lineItems;

    public OrderLineItems() {
    }

    public OrderLineItems(List<OrderLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public List<OrderLineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<OrderLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public OrderLineItem findOrderLineItem(String lineItemId) {
        return lineItems.stream()
                .filter(lineItem -> lineItem.getMenuItemId().equals(lineItemId))
                .findFirst()
                .get();
    }

    public Money changeToOrderTotal(OrderRevision orderRevision) {
        AtomicReference<Money> delta = new AtomicReference<>();
        orderRevision.getRevisedLineItemQuantities().forEach((lineItemId, newQuantity) -> {
            OrderLineItem lineItem = findOrderLineItem(lineItemId);
            delta.set(delta.get().add(lineItem.deltaForChangedQuantity(newQuantity)));
        });
        return delta.get();
    }

    public void updateLineItems(OrderRevision orderRevision) {
        this.getLineItems().forEach(lineItem -> {
            Integer revised = orderRevision.getRevisedLineItemQuantities().get(lineItem.getMenuItemId());
            lineItem.setQuantity(revised);
        });
    }

    public Money orderTotal() {
        return lineItems.stream()
                .map(OrderLineItem::getTotal)
                .reduce(Money.ZERO, Money::add);
    }

    public LineItemQuantityChange lineItemQuantityChange(OrderRevision orderRevision) {
        Money currentOrderTotal = orderTotal();
        Money delta = changeToOrderTotal(orderRevision);
        Money newOrderTotal = currentOrderTotal.add(delta);
        return new LineItemQuantityChange(currentOrderTotal, newOrderTotal, delta);
    }
}
