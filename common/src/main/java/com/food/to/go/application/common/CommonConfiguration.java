package com.food.to.go.application.common;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

    public CommonJsonMapperInitializer commonJsonMapperInitializer() {
        return new CommonJsonMapperInitializer();
    }
}
