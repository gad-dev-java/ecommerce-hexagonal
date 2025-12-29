package com.gad.ecommerce.order.infrastructure.adapters.input.rest.model.request;

public record ShippingAddressRequest(
        String street,
        String city,
        String state,
        String postalCode,
        String country
) {
}
