package com.gad.ecommerce.order.application.ports.input.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record OrderItemCommand(
        UUID productId,
        String productTitle,
        Integer quantity,
        BigDecimal unitPrice
) {
}
