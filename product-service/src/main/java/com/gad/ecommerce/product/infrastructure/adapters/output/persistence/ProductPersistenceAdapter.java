package com.gad.ecommerce.product.infrastructure.adapters.output.persistence;

import com.gad.ecommerce.product.application.ports.input.dto.ProductSearchQuery;
import com.gad.ecommerce.product.application.ports.output.ProductPersistencePort;
import com.gad.ecommerce.product.domain.exception.ProductNotFoundException;
import com.gad.ecommerce.product.domain.model.Product;
import com.gad.ecommerce.product.infrastructure.adapters.output.persistence.entities.ProductEntity;
import com.gad.ecommerce.product.infrastructure.adapters.output.persistence.mapper.ProductMapper;
import com.gad.ecommerce.product.infrastructure.adapters.output.persistence.repository.ProductRepository;
import com.gad.ecommerce.product.infrastructure.adapters.output.persistence.specification.SearchProductSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductPersistenceAdapter implements ProductPersistencePort {
    private final ProductRepository  productRepository;
    private final ProductMapper productMapper;

    @Override
    public Optional<Product> getProductById(UUID id) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found."));

        return Optional.of(productMapper.toDomain(entity));
    }

    @Override
    public Page<Product> getPageProductsFilteredAndPaginated(Pageable pageable, ProductSearchQuery query) {
        SearchProductSpecification productSpecification = new SearchProductSpecification(query.title(),
                query.description(), query.minPrice(), query.maxPrice(), query.currency(), query.category(), query.conditionType(), query.status());

        Page<ProductEntity> productEntityPage = productRepository.findAll(productSpecification, pageable);

        return productEntityPage.map(productMapper::toDomain);
    }

    @Override
    public Product saveProduct(Product product) {
        return null;
    }
}
