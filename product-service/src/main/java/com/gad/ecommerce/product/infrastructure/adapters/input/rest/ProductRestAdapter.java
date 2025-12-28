package com.gad.ecommerce.product.infrastructure.adapters.input.rest;

import com.gad.ecommerce.product.application.ports.input.GetProductsFilteredUseCase;
import com.gad.ecommerce.product.application.ports.input.dto.PaginatedResponse;
import com.gad.ecommerce.product.application.ports.input.dto.PaginationFilter;
import com.gad.ecommerce.product.application.ports.input.dto.ProductDto;
import com.gad.ecommerce.product.application.ports.input.dto.ProductFilterParams;
import com.gad.ecommerce.product.infrastructure.adapters.input.rest.mapper.ProductRestMapper;
import com.gad.ecommerce.product.infrastructure.adapters.input.rest.model.dto.ProductSummaryDto;
import com.gad.ecommerce.product.infrastructure.adapters.input.rest.model.response.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductRestAdapter {
    private final GetProductsFilteredUseCase productsFilteredUseCase;
    private final ProductRestMapper productRestMapper;

    @GetMapping
    public ResponseEntity<?> getProductsPaginatedAndFiltered(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size,
                                                             @RequestParam(defaultValue = "createdAt") String sortBy,
                                                             @RequestParam(defaultValue = "asc") String sortDirection,
                                                             @RequestParam(required = false) String title,
                                                             @RequestParam(required = false) String description,
                                                             @RequestParam(required = false) BigDecimal minPrice,
                                                             @RequestParam(required = false) BigDecimal maxPrice,
                                                             @RequestParam(required = false) String currency,
                                                             @RequestParam(required = false) String category,
                                                             @RequestParam(required = false) String conditionType) {
        PaginationFilter paginationFilter = new PaginationFilter(page, size, sortBy, sortDirection);
        ProductFilterParams filterParams = new ProductFilterParams(title, description, minPrice, maxPrice, currency, category, conditionType);

        PaginatedResponse<ProductDto> productDtoPaginatedResponse = productsFilteredUseCase.getProductListFilteredAndPaginated(paginationFilter, filterParams);

        List<ProductSummaryDto> productSummaryDtoList = productDtoPaginatedResponse.content()
                .stream()
                .map(productRestMapper::toSummary)
                .toList();

        PaginatedResponse<ProductSummaryDto> productSummaryDtoPaginatedResponse = new PaginatedResponse<>(
                productSummaryDtoList,
                productDtoPaginatedResponse.pageNumber(),
                productDtoPaginatedResponse.pageSize(),
                productDtoPaginatedResponse.totalElements(),
                productDtoPaginatedResponse.totalPages(),
                productDtoPaginatedResponse.isLast()
        );

        DataResponse<PaginatedResponse<ProductSummaryDto>> dataResponse = DataResponse.<PaginatedResponse<ProductSummaryDto>>builder()
                .status(HttpStatus.OK.value())
                .message("Products retrieved successfully")
                .data(productSummaryDtoPaginatedResponse)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(dataResponse);
    }
}
