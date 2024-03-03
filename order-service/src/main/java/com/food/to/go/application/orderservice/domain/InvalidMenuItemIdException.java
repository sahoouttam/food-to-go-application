package com.food.to.go.application.orderservice.domain;

public class InvalidMenuItemIdException extends RuntimeException {

    public InvalidMenuItemIdException(String message) {
        super("Invalid menu item id " + message);
    }
}
