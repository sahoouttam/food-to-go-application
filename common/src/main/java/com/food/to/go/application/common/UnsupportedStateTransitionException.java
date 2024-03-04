package com.food.to.go.application.common;

public class UnsupportedStateTransitionException extends RuntimeException {

    public UnsupportedStateTransitionException(Enum message) {
        super("current state: " + message);
    }
}
