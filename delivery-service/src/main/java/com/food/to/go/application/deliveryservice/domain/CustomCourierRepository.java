package com.food.to.go.application.deliveryservice.domain;

import java.util.List;

public interface CustomCourierRepository {

    Courier findOrCreateCourier(long courierId);
}
