package com.gad.ecommerce.product.infrastructure.adapters.input.rest.model.dto;

public record CategorySummaryDto(
        String id,
        String name,
        ParentSummaryDto parent
) {
}
