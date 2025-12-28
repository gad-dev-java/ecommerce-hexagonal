package com.gad.ecommerce.product.application.ports.input.dto;

import com.gad.ecommerce.product.domain.enums.ConditionType;
import com.gad.ecommerce.product.domain.enums.ProductStatus;
import com.gad.ecommerce.product.domain.model.Category;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record ProductDto(
        UUID id,
        UUID sellerId,
        Category category,
        String title,
        String description,
        BigDecimal price,
        String currency,
        Integer stock,
        ConditionType conditionType,
        ProductStatus status,
        String imageUrl,
        Long version,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
