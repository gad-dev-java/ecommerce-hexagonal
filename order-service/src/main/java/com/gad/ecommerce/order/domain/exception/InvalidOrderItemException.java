package com.gad.ecommerce.order.domain.exception;

public class InvalidOrderItemException extends RuntimeException{
    public InvalidOrderItemException(String message) {
        super(message);
    }
}
