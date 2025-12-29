package com.gad.ecommerce.order.application.ports.input.dto;

import com.gad.ecommerce.order.domain.model.ShippingAddress;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record CreateOrderCommand(
        UUID buyerId,
        UUID sellerId,
        ShippingAddress shippingAddress,
        List<OrderItemCommand> items
) {
}
