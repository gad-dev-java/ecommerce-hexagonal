package com.gad.ecommerce.product.infrastructure.adapters.input.rest.model.request;

import jakarta.validation.constraints.Min;
import lombok.NonNull;

public record ReserveStockRequest(
        @NonNull
        @Min(value = 1, message = "The minimum quantity is 1")
        Integer quantity
) {
}
