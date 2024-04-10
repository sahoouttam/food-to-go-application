package com.food.to.go.application.orderservice.grpc;

import com.food.to.go.application.orderservice.domain.OrderService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

public class OrderServiceServer {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceServer.class);
    private int port = 50051;
    private Server server;
    private OrderService orderService;

    public OrderServiceServer(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostConstruct
    public void start() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(new OrderServiceImpl())
                .build()
                .start();
    }

    @PreDestroy
    public void stop() {
        if (server != null) {
            logger.info("*** shutting down gRPC server since JVM is shutting down");
            server.shutdown();
            logger.info("*** server shut down");
        }
    }

    private class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {

    }
}
