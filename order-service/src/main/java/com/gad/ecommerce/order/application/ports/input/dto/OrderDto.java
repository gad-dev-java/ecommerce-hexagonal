package com.gad.ecommerce.order.application.ports.input.dto;

import com.gad.ecommerce.order.domain.enums.OrderStatus;
import com.gad.ecommerce.order.domain.model.OrderItem;
import com.gad.ecommerce.order.domain.model.ShippingAddress;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
public record OrderDto(
        UUID id,
        String orderNumber,
        UUID buyerId,
        UUID sellerId,
        BigDecimal totalAmount,
        BigDecimal shippingCost,
        OrderStatus status,
        ShippingAddress shippingAddress,
        List<OrderItem> items
) {
}
