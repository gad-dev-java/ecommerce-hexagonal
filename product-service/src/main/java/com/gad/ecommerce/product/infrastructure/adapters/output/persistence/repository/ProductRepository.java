package com.gad.ecommerce.product.infrastructure.adapters.output.persistence.repository;

import com.gad.ecommerce.product.infrastructure.adapters.output.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID>, JpaSpecificationExecutor<ProductEntity> {
}
