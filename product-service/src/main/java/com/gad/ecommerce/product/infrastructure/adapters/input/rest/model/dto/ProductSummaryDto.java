package com.gad.ecommerce.product.infrastructure.adapters.input.rest.model.dto;

import com.gad.ecommerce.product.domain.enums.ConditionType;
import com.gad.ecommerce.product.domain.enums.ProductStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductSummaryDto(
        UUID id,
        UUID sellerId,
        CategorySummaryDto category,
        String title,
        String description,
        BigDecimal price,
        String currency,
        Integer stock,
        ConditionType conditionType,
        ProductStatus status,
        String imageUrl,
        LocalDateTime createdAt
) {
}
