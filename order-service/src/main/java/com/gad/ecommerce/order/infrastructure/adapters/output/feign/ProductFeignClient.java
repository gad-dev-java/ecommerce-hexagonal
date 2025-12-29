package com.gad.ecommerce.order.infrastructure.adapters.output.feign;

import com.gad.ecommerce.order.application.ports.input.dto.ProductSummaryDto;
import com.gad.ecommerce.order.infrastructure.adapters.input.rest.model.request.ReserveStockRequest;
import com.gad.ecommerce.order.infrastructure.adapters.input.rest.model.response.DataResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service", path = "api/v1/products")
public interface ProductFeignClient {
    @GetMapping("/{id}")
    DataResponse<ProductSummaryDto> getProductById(@PathVariable String id);

    @PostMapping("/{id}/reserve")
    DataResponse<ProductSummaryDto> reserveStock(@PathVariable String id, @RequestBody ReserveStockRequest request);
}
