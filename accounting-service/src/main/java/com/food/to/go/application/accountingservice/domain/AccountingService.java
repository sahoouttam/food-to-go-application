package com.food.to.go.application.accountingservice.domain;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.SaveOptions;
import io.eventuate.sync.AggregateRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AccountingService {

    @Autowired
    private AggregateRepository<Account, AccountCommand> accountRepository;


    public void create(String aggregateId) {
        EntityWithIdAndVersion<Account> account = accountRepository.save(new CreateAccountCommand(),
                Optional.of(new SaveOptions().withId(aggregateId)));
    }
}
