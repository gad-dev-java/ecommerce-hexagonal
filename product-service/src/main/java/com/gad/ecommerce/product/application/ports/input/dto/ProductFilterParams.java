package com.gad.ecommerce.product.application.ports.input.dto;

import java.math.BigDecimal;

public record ProductFilterParams(
        String title,
        String description,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        String currency,
        String category,
        String conditionType
) {
}
