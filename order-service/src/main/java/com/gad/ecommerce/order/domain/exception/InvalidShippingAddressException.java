package com.gad.ecommerce.order.domain.exception;

public class InvalidShippingAddressException extends RuntimeException{
    public InvalidShippingAddressException(String message) {
        super(message);
    }
}
