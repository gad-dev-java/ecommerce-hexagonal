package com.gad.ecommerce.product.application.service;

import com.gad.ecommerce.product.application.ports.input.GetProductsFilteredUseCase;
import com.gad.ecommerce.product.application.ports.input.dto.*;
import com.gad.ecommerce.product.application.ports.output.ProductPersistencePort;
import com.gad.ecommerce.product.domain.enums.ProductStatus;
import com.gad.ecommerce.product.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductsFilteredService implements GetProductsFilteredUseCase {
    private static final String DEFAULT_SORT_BY = "createdAt";
    private static final String DEFAULT_PRODUCT_STATUS_QUERY = ProductStatus.ACTIVE.name();
    private final ProductPersistencePort productPersistencePort;

    @Override
    public PaginatedResponse<ProductDto> getProductListFilteredAndPaginated(PaginationFilter paginationFilter, ProductFilterParams filterParams) {
        ProductSearchQuery query = ProductSearchQuery.builder()
                .title(filterParams.title())
                .description(filterParams.description())
                .minPrice(filterParams.minPrice())
                .maxPrice(filterParams.maxPrice())
                .currency(filterParams.currency())
                .category(filterParams.category())
                .status(DEFAULT_PRODUCT_STATUS_QUERY)
                .conditionType(filterParams.conditionType())
                .build();

        Sort.Direction direction = resolveSortDirection(paginationFilter.sortDirection());

        String sortBy = DEFAULT_SORT_BY;
        if (paginationFilter.sortBy() != null && !paginationFilter.sortBy().isEmpty()) {
            sortBy = paginationFilter.sortBy();
        }

        Sort.Order order = new Sort.Order(direction, sortBy).ignoreCase();

        PageRequest pageRequest = PageRequest.of(
                paginationFilter.pageNumber(),
                paginationFilter.pageSize(),
                Sort.by(order)
        );

        Page<Product> productPage = productPersistencePort.getPageProductsFilteredAndPaginated(pageRequest, query);
        List<ProductDto> productDtoList = productPage.stream()
                .map(this::getProductDto)
                .toList();

        return new PaginatedResponse<>(productDtoList, productPage.getNumber(),
                productPage.getSize(),
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.isLast());
    }

    private ProductDto getProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .sellerId(product.getSellerId())
                .category(product.getCategory())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .stock(product.getStock())
                .conditionType(product.getConditionType())
                .status(product.getStatus())
                .imageUrl(product.getImageUrl())
                .version(product.getVersion())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    private Sort.Direction resolveSortDirection(String sortDirection) {
        if (!StringUtils.hasText(sortDirection)) {
            return Sort.Direction.ASC;
        }
        return Sort.Direction.fromString(sortDirection);
    }
}
