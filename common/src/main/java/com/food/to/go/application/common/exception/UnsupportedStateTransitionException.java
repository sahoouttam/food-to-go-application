package com.food.to.go.application.common.exception;

public class UnsupportedStateTransitionException extends RuntimeException {

    public UnsupportedStateTransitionException(String message) {
        super(message);
    }
}
