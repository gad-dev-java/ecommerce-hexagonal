package com.gad.ecommerce.product.domain.model;

import com.gad.ecommerce.product.domain.exception.InvalidCategoryException;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Category {
    private UUID id;
    private String name;
    private UUID parentId;
    private LocalDateTime createdAt;

    public Category(String name, UUID parentId) {
        this.name = name;
        this.parentId = parentId;
        this.createdAt = LocalDateTime.now();

        this.validate();
    }

    @Builder
    public Category(UUID id, String name, UUID parentId, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.createdAt = createdAt;
    }

    private void validate() {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidCategoryException("name cannot be empty");
        }
    }
}
