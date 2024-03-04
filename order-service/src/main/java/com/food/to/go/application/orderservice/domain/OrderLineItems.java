package com.food.to.go.application.orderservice.domain;

import com.food.to.go.application.api.events.OrderLineItem;
import com.food.to.go.application.common.Money;
import com.food.to.go.application.common.RevisedOrderLineItem;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import java.util.List;
import java.util.Optional;

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
        return orderRevision.getRevisedOrderLineItems()
                .stream()
                .map(item -> {
                    OrderLineItem lineItem = findOrderLineItem(item.getMenuItemId());
                    return lineItem.deltaForChangedQuantity(item.getQuantity());
                })
                .reduce(Money.ZERO, Money::add);
    }

    public void updateLineItems(OrderRevision orderRevision) {
        this.getLineItems().forEach(lineItem -> {
            Optional<Integer> revised = orderRevision.getRevisedOrderLineItems()
                    .stream()
                    .filter(item -> item.getMenuItemId().equals(lineItem.getMenuItemId()))
                    .map(RevisedOrderLineItem::getQuantity)
                    .findFirst();

            lineItem.setQuantity(revised.orElseThrow(() ->
                    new IllegalArgumentException(String.format("menu item id not found.", lineItem.getMenuItemId()))));
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
