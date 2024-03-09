package com.food.to.go.application.accountingservice.domain;

import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import io.eventuate.tram.sagas.eventsourcingsupport.SagaReplyRequestedEvent;

import java.util.Collections;
import java.util.List;

import static io.eventuate.EventUtil.events;

public class Account extends ReflectiveMutableCommandProcessingAggregate<Account, AccountCommand> {

    public List<Event> process(CreateAccountCommand createAccountCommand) {
        return events(new AccountCreatedEvent());
    }

    public void apply(AccountCreatedEvent accountCreatedEvent) {

    }

    public List<Event> process(AuthorizeCommandInternal authorizeCommandInternal) {
        return events(new AccountAuthorizedEvent());
    }

    public List<Event> process(ReverseAuthorizeCommandInternal reverseAuthorizeCommandInternal) {
        return Collections.emptyList();
    }

    public List<Event> process(ReviseAuthorizeCommandInternal reviseAuthorizeCommandInternal) {
        return Collections.emptyList();
    }

    public void apply(AccountAuthorizedEvent accountAuthorizedEvent) {

    }

    public void apply(SagaReplyRequestedEvent sagaReplyRequestedEvent) {

    }
}
