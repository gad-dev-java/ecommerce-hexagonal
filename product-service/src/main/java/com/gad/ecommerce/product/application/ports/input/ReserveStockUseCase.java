package com.gad.ecommerce.product.application.ports.input;

import com.gad.ecommerce.product.application.ports.input.dto.ProductDto;

import java.util.UUID;

public interface ReserveStockUseCase {
    ProductDto reserveStock(UUID productId, Integer quantity);
}
