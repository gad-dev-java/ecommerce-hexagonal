package com.gad.ecommerce.product.application.ports.input.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductSearchQuery(
        String title,
        String description,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        String currency,
        String category,
        String conditionType,
        String status
) {
}
