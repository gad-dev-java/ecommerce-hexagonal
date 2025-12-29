package com.gad.ecommerce.order.application.ports.output;

import com.gad.ecommerce.order.application.ports.input.dto.ProductSummaryDto;

import java.util.UUID;

public interface ProductExternalPort {
    ProductSummaryDto getProductSummary(UUID productId);
}
