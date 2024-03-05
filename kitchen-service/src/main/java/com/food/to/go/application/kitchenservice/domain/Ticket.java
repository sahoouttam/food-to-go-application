package com.food.to.go.application.kitchenservice.domain;

import com.food.to.go.application.common.NotYetImplementedException;
import com.food.to.go.application.common.RevisedOrderLineItem;
import com.food.to.go.application.common.UnsupportedStateTransitionException;
import com.food.to.go.application.kitchenservice.api.TicketDetails;
import com.food.to.go.application.kitchenservice.api.TicketLineItem;
import com.food.to.go.application.kitchenservice.api.events.TicketAcceptedEvent;
import com.food.to.go.application.kitchenservice.api.events.TicketCancelled;
import com.food.to.go.application.kitchenservice.api.events.TicketDomainEvent;
import io.eventuate.tram.events.aggregates.ResultWithDomainEvents;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private TicketState state;

    @Enumerated(EnumType.STRING)
    private TicketState previousState;

    private Long restaurantId;

    @ElementCollection
    @CollectionTable(name = "ticket_line_items")
    private List<TicketLineItem> lineItems;

    private LocalDateTime readyBy;
    private LocalDateTime acceptTime;
    private LocalDateTime preparingTime;
    private LocalDateTime pickedUpTime;
    private LocalDateTime readyForPickupTime;

    private Ticket() {
    }

    public Ticket(long restaurantId, Long id, TicketDetails details) {
        this.restaurantId = restaurantId;
        this.id = id;
        this.state = TicketState.CREATE_PENDING;
        this.lineItems = details.getLineItems();
    }

    public Long getId() {
        return id;
    }

    public static ResultWithDomainEvents<Ticket, TicketDomainEvent> create(long restaurantId, long id, TicketDetails details) {
        return new ResultWithDomainEvents<>(new Ticket(restaurantId, id, details));
    }

    public List<TicketDomainEvent> confirmCreate() {
        switch (state) {
            case CANCEL_PENDING:
                state = TicketState.AWAITING_ACCEPTANCE;
                return Collections.singletonList(new TicketCreatedEvent(id, new TicketDetails()));
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public List<TicketDomainEvent> cancelCreate() {
        throw new NotYetImplementedException();
    }

    public List<TicketDomainEvent> accept(LocalDateTime readyBy) {
        switch (state) {
            case AWAITING_ACCEPTANCE:
                this.acceptTime = LocalDateTime.now();
                if (!acceptTime.isBefore(readyBy)) {
                    throw new IllegalArgumentException(String.format("readyBy %s is not after now %s", readyBy, acceptTime));
                }
                this.readyBy = readyBy;
                return Collections.singletonList(new TicketAcceptedEvent(readyBy));
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public List<TicketDomainEvent> preparing() {
        switch (state) {
            case ACCEPTED:
                this.state = TicketState.PREPARING;
                this.preparingTime = LocalDateTime.now();
                return Collections.singletonList(new TicketPreparationStartedEvent());
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public List<TicketDomainEvent> readyForPickup() {
        switch (state) {
            case PREPARING:
                this.state = TicketState.READY_FOR_PICKUP;
                this.readyForPickupTime = LocalDateTime.now();
                return Collections.singletonList(new TicketPreparationCompletedEvent());
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public List<TicketDomainEvent> pickUp() {
        switch (state) {
            case READY_FOR_PICKUP:
                this.state = TicketState.PICKED_UP;
                this.pickedUpTime = LocalDateTime.now();
                return Collections.singletonList(new TicketPickedUpEvent());
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public void changeLineItemQuantity() {
        switch (state) {
            case AWAITING_ACCEPTANCE:
            case PREPARING:
                break;
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public List<TicketDomainEvent> cancel() {
        switch (state) {
            case AWAITING_ACCEPTANCE:
            case ACCEPTED:
                this.previousState = this.state;
                this.state = TicketState.CANCEL_PENDING;
                return Collections.emptyList();
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public List<TicketDomainEvent> confirmCancel() {
        switch (state) {
            case CANCEL_PENDING:
                this.state = TicketState.CANCELLED;
                return Collections.singletonList(new TicketCancelled());
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public List<TicketDomainEvent> undoCancel() {
        switch (state) {
            case CANCEL_PENDING:
                this.state = this.previousState;
                return Collections.emptyList();
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public List<TicketDomainEvent> beginReviseOrder(List<RevisedOrderLineItem> revisedOrderLineItems) {
        switch (state) {
            case AWAITING_ACCEPTANCE:
            case ACCEPTED:
                this.previousState = this.state;
                this.state = TicketState.REVISION_PENDING;
                return Collections.emptyList();
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public List<TicketDomainEvent> undoBeginReviseOrder() {
        switch (state) {
            case REVISION_PENDING:
                this.state = this.previousState;
                return Collections.emptyList();
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

    public List<TicketDomainEvent> confirmReviseTicket(List<RevisedOrderLineItem> revisedOrderLineItems) {
        switch (state) {
            case REVISION_PENDING:
                this.state = this.previousState;
                return Collections.singletonList(new TicketRevised());
            default:
                throw new UnsupportedStateTransitionException(state);
        }
    }

}
