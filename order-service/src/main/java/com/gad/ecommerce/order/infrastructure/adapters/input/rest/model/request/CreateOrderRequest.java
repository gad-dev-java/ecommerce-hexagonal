package com.gad.ecommerce.order.infrastructure.adapters.input.rest.model.request;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(
        UUID buyerId,
        UUID sellerId,
        ShippingAddressRequest shippingAddress,
        List<OrderItemRequest> items
) {
}
