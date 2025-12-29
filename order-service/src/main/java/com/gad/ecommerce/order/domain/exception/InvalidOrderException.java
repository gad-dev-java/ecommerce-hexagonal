package com.gad.ecommerce.order.domain.exception;

public class InvalidOrderException extends RuntimeException{
    public InvalidOrderException(String message) {
        super(message);
    }
}
