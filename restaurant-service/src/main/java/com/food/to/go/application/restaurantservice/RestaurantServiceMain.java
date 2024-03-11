package com.food.to.go.application.restaurantservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.eventuate.common.json.mapper.JSonMapper;
import io.eventuate.tram.spring.jdbckafka.TramJdbcKafkaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(TramJdbcKafkaConfiguration.class)
public class RestaurantServiceMain {

    public ObjectMapper objectMapper() {
        return JSonMapper.objectMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestaurantServiceMain.class, args);
    }
}
