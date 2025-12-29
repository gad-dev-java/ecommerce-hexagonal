package com.gad.ecommerce.order.infrastructure.adapters.output.feign;

import com.gad.ecommerce.order.application.ports.input.dto.ProductSummaryDto;
import com.gad.ecommerce.order.application.ports.output.ProductExternalPort;
import com.gad.ecommerce.order.infrastructure.adapters.input.rest.model.request.ReserveStockRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductExternalAdapter implements ProductExternalPort {
    private final ProductFeignClient productFeignClient;

    @Override
    public ProductSummaryDto getProductSummary(UUID productId) {
        var response = productFeignClient.getProductById(productId.toString());
        return ProductSummaryDto.builder()
                .id(response.data().id())
                .sellerId(response.data().sellerId())
                .title(response.data().title())
                .price(response.data().price())
                .currency(response.data().currency())
                .stock(response.data().stock())
                .conditionType(response.data().conditionType())
                .status(response.data().status())
                .imageUrl(response.data().imageUrl())
                .build();
    }

    @Override
    public void reserveStock(UUID productId, Integer quantity) {
        ReserveStockRequest request = new ReserveStockRequest(quantity);
        productFeignClient.reserveStock(productId.toString(), request);
    }
}
