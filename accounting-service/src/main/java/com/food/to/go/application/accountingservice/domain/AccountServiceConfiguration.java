package com.food.to.go.application.accountingservice.domain;

import com.food.to.go.application.common.CommonConfiguration;
import io.eventuate.sync.AggregateRepository;
import io.eventuate.sync.EventuateAggregateStore;
import io.eventuate.tram.spring.commands.producer.TramCommandProducerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({TramCommandProducerConfiguration.class, CommonConfiguration.class})
public class AccountServiceConfiguration {

    @Bean
    public AggregateRepository<Account, AccountCommand> accountRepositorySync(EventuateAggregateStore eventuateAggregateStore) {
        return new AggregateRepository<>(Account.class, eventuateAggregateStore);
    }

    @Bean
    public AccountingService accountingService() {
        return new AccountingService();
    }
}
