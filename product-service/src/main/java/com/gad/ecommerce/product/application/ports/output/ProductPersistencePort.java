package com.gad.ecommerce.product.application.ports.output;

import com.gad.ecommerce.product.application.ports.input.dto.ProductSearchQuery;
import com.gad.ecommerce.product.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface ProductPersistencePort {
    Optional<Product> getProductById(UUID id);
    Page<Product> getPageProductsFilteredAndPaginated(Pageable pageable, ProductSearchQuery query);
    Product saveProduct(Product product);
}
