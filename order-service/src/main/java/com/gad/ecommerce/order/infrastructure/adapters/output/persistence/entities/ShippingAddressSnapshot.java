package com.gad.ecommerce.order.infrastructure.adapters.output.persistence.entities;

import com.gad.ecommerce.order.domain.exception.InvalidShippingAddressException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingAddressSnapshot {
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
