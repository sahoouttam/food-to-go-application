package com.food.to.go.application.deliveryservice.domain;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan
@EnableJpaRepositories
@EnableTransactionManagement
public class DeliveryServiceDomainConfiguration {

    public DeliveryService deliveryService(RestaurantRepository restaurantRepository,
                                           DeliveryRepository deliveryRepository,
                                           CourierRepository courierRepository) {
        return new DeliveryService(restaurantRepository, deliveryRepository, courierRepository);
    }
}
