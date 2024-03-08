package com.food.to.go.application.deliveryservice.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourierRepository extends CrudRepository<Courier, Long>, CustomCourierRepository {

    @Query("SELECT courier FROM Courier courier WHERE courier.available = true")
    List<Courier> findAllAvailable();
}
