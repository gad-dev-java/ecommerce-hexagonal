package com.gad.ecommerce.product.application.service;

import com.gad.ecommerce.product.application.ports.input.FindProductUseCase;
import com.gad.ecommerce.product.application.ports.input.dto.ProductDto;
import com.gad.ecommerce.product.application.ports.output.ProductPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindProductService implements FindProductUseCase {
    private final ProductPersistencePort productPersistencePort;

    @Override
    public ProductDto findProductById(UUID id) {
        return null;
    }
}
