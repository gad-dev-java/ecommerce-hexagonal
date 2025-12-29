package com.gad.ecommerce.order.application.ports.input.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record ProductSummaryDto(
        UUID id,
        UUID sellerId,
        String title,
        BigDecimal price,
        String currency,
        Integer stock,
        String conditionType,
        String status,
        String imageUrl
) {
}
