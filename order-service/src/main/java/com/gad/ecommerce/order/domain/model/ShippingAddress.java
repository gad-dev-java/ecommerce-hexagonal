package com.gad.ecommerce.order.domain.model;

import com.gad.ecommerce.order.domain.exception.InvalidShippingAddressException;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ShippingAddress {
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    @Builder
    public ShippingAddress(String street, String city, String state, String postalCode, String country) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.validate();
    }

    private void validate() {
        if (street == null|| street.trim().isEmpty()){
            throw new InvalidShippingAddressException("Street address cannot be empty");
        }
        if (city == null || city.trim().isEmpty()) {
            throw new InvalidShippingAddressException("City address cannot be empty");
        }
        if (country == null || country.trim().isEmpty()) {
            throw new  InvalidShippingAddressException("Country address cannot be empty");
        }
    }
}
