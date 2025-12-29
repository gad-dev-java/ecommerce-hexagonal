package com.gad.ecommerce.product.infrastructure.adapters.input.rest.model.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(
        int status,
        String message,
        Object errors,
        LocalDateTime timestamp,
        String path
) {
}
