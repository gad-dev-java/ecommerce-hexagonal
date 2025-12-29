package com.gad.ecommerce.product.application.service;

import com.gad.ecommerce.product.application.ports.input.ReserveStockUseCase;
import com.gad.ecommerce.product.application.ports.input.dto.ProductDto;
import com.gad.ecommerce.product.application.ports.output.ProductPersistencePort;
import com.gad.ecommerce.product.domain.exception.ProductNotFoundException;
import com.gad.ecommerce.product.domain.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReserveStockService implements ReserveStockUseCase {
    private final ProductPersistencePort productPersistencePort;

    @Override
    @Transactional
    public ProductDto reserveStock(UUID productId, Integer quantity) {
        Product productFound = productPersistencePort.getProductById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id " + productId));

        productFound.reserveStock(quantity);

        Product productSaved = productPersistencePort.saveProduct(productFound);
        log.info("Product saved: {}",productSaved);
        return toDto(productSaved);
    }

    private ProductDto toDto(Product product) {
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
}
