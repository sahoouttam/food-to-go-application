package com.food.to.go.application.orderservice.sagas.cancelorder;

import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;

public class CancelOrderSaga implements SimpleSaga<CancelOrderSagaData> {

    private SagaDefinition<CancelOrderSagaData> sagaDefinition;

    @Override
    public SagaDefinition<CancelOrderSagaData> getSagaDefinition() {
        return null;
    }
}
