package com.gad.ecommerce.product.application.ports.input.dto;

public record PaginationFilter(
        int pageNumber,
        int pageSize,
        String sortBy,
        String sortDirection
) {
}
