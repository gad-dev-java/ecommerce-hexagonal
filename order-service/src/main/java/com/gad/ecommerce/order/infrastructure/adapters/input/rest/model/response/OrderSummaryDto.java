package com.gad.ecommerce.order.infrastructure.adapters.input.rest.model.response;

import com.gad.ecommerce.order.domain.enums.OrderStatus;
import com.gad.ecommerce.order.domain.model.OrderItem;
import com.gad.ecommerce.order.domain.model.ShippingAddress;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record OrderSummaryDto(
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
