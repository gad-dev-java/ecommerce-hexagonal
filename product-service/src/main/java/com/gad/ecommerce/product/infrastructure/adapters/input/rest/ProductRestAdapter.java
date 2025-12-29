package com.gad.ecommerce.product.infrastructure.adapters.input.rest;

import com.gad.ecommerce.product.application.ports.input.FindProductUseCase;
import com.gad.ecommerce.product.application.ports.input.GetProductsFilteredUseCase;
import com.gad.ecommerce.product.application.ports.input.dto.PaginatedResponse;
import com.gad.ecommerce.product.application.ports.input.dto.PaginationFilter;
import com.gad.ecommerce.product.application.ports.input.dto.ProductDto;
import com.gad.ecommerce.product.application.ports.input.dto.ProductFilterParams;
import com.gad.ecommerce.product.infrastructure.adapters.input.rest.mapper.ProductRestMapper;
import com.gad.ecommerce.product.infrastructure.adapters.input.rest.model.dto.ProductSummaryDto;
import com.gad.ecommerce.product.infrastructure.adapters.input.rest.model.request.ReserveStockRequest;
import com.gad.ecommerce.product.infrastructure.adapters.input.rest.model.response.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductRestAdapter {
    private final GetProductsFilteredUseCase productsFilteredUseCase;
    private final FindProductUseCase findProductUseCase;
    private final ProductRestMapper productRestMapper;

    @GetMapping
    public ResponseEntity<DataResponse<PaginatedResponse<ProductSummaryDto>>> getProductsPaginatedAndFiltered(@RequestParam(defaultValue = "0") int page,
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

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<ProductSummaryDto>> getProductById(@PathVariable String id) {
        ProductDto productDto = findProductUseCase.findProductById(UUID.fromString(id));
        ProductSummaryDto productSummaryDto = productRestMapper.toSummary(productDto);

        DataResponse<ProductSummaryDto> dataResponse = DataResponse.<ProductSummaryDto>builder()
                .status(HttpStatus.OK.value())
                .message("Product retrieved successfully")
                .data(productSummaryDto)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(dataResponse);
    }

    @PostMapping("/{id}/reserve")
    public ResponseEntity<DataResponse<ProductSummaryDto>> reserveStock(@PathVariable String id, @RequestBody ReserveStockRequest request) {
        ProductDto productDto = findProductUseCase.findProductById(UUID.fromString(id));
        ProductSummaryDto productSummaryDto = productRestMapper.toSummary(productDto);

        DataResponse<ProductSummaryDto> dataResponse = DataResponse.<ProductSummaryDto>builder()
                .status(HttpStatus.OK.value())
                .message("stock reserved successfully")
                .data(productSummaryDto)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(dataResponse);
    }
}
