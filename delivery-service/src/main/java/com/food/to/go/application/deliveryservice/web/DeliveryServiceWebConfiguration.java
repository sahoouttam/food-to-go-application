package com.food.to.go.application.deliveryservice.web;

import com.food.to.go.application.common.CommonConfiguration;
import com.food.to.go.application.deliveryservice.domain.DeliveryServiceDomainConfiguration;
import org.springframework.context.annotation.Import;

@Import({DeliveryServiceDomainConfiguration.class, CommonConfiguration.class})
public class DeliveryServiceWebConfiguration {
}
