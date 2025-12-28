package com.gad.ecommerce.product.infrastructure.adapters.output.persistence.repository;

import com.gad.ecommerce.product.infrastructure.adapters.output.persistence.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
}
