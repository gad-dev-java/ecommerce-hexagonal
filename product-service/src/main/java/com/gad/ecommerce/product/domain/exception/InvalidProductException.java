package com.gad.ecommerce.product.domain.exception;

public class InvalidProductException extends RuntimeException{
    public InvalidProductException(String message) {
        super(message);
    }
}
