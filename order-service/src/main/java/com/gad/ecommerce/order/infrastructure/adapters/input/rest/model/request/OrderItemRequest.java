package com.gad.ecommerce.order.infrastructure.adapters.input.rest.model.request;

import java.math.BigDecimal;

public record OrderItemRequest(
        String productId,
        String productTitle,
        Integer quantity,
        BigDecimal unitPrice
) {
}
