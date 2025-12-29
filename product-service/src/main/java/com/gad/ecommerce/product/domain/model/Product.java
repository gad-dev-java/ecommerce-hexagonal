package com.gad.ecommerce.product.domain.model;

import com.gad.ecommerce.product.domain.enums.ConditionType;
import com.gad.ecommerce.product.domain.enums.ProductStatus;
import com.gad.ecommerce.product.domain.exception.InsufficientStockException;
import com.gad.ecommerce.product.domain.exception.InvalidProductException;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
public class Product {
    private UUID id;
    private UUID sellerId;
    private Category category;
    private String title;
    private String description;
    private String currency;
    private BigDecimal price;
    private Integer stock;
    private ConditionType conditionType;
    private ProductStatus status;
    private String imageUrl;
    private Long version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product(UUID sellerId, Category category, String title, String description, BigDecimal price, String currency, Integer stock,
                   ConditionType conditionType, String imageUrl) {
        this.sellerId = sellerId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.stock = stock;
        this.conditionType = conditionType;
        this.status = ProductStatus.ACTIVE;
        this.imageUrl = imageUrl;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        this.validate();
    }

    @Builder
    public Product(UUID id, UUID sellerId, Category category, String title, String description, BigDecimal price, String currency, Integer stock,
                   ConditionType conditionType, ProductStatus status, String imageUrl, Long version, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.sellerId = sellerId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.stock = stock;
        this.conditionType = conditionType;
        this.status = status;
        this.imageUrl = imageUrl;
        this.version = version;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void reduceStock(Integer quantity) {
        if (quantity <= 0) {
            throw new InvalidProductException("Quantity must be greater than zero.");
        }
        if (this.stock < quantity) {
            throw new InvalidProductException("Stock insufficient for the product: " + this.title);
        }
        this.stock = this.stock - quantity;

        if (this.stock == 0) {
            this.status = ProductStatus.SOLD_OUT;
        }
        this.updatedAt = LocalDateTime.now();
    }

    public void reserveStock(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new InvalidProductException("Quantity must be greater than zero.");
        }

        if (this.stock < quantity) {
            throw new InsufficientStockException(String.format("Insufficient stock. Requested: %d, Available: %d", quantity, this.stock));
        }

        this.stock -= quantity;
        if (this.stock == 0) {
            this.status = ProductStatus.SOLD_OUT;
        }
        this.updatedAt = LocalDateTime.now();
    }

    public void updatePrice(BigDecimal newPrice) {
        if (newPrice == null || newPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidProductException("Price must be greater than zero.");
        }
        this.price = newPrice;
        this.updatedAt = LocalDateTime.now();
    }

    private void validate() {
        Objects.requireNonNull(sellerId, "sellerId is null");
        Objects.requireNonNull(category, "category is required");

        if (title == null || title.trim().isEmpty()) {
            throw new InvalidProductException("title is required");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new InvalidProductException("description is required");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidProductException("price should be greater than zero");
        }
        if (currency == null || currency.trim().isEmpty()) {
            throw new InvalidProductException("currency is required");
        }
        if (stock == null || stock < 0) {
            throw new InvalidProductException("stock cannot be negative");
        }
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            throw new InvalidProductException("imageUrl is required");
        }
        Objects.requireNonNull(conditionType, "conditionType is required");
    }
}
