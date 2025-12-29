package com.gad.ecommerce.order.domain.model;

import com.gad.ecommerce.order.domain.exception.InvalidOrderItemException;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@ToString
public class OrderItem {
    private UUID id;
    private UUID productId;
    private String productTitle;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;

    public OrderItem(UUID productId, String productTitle, Integer quantity, BigDecimal unitPrice) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        calculateSubtotal();
        this.validate();
    }

    @Builder
    public OrderItem(UUID id, UUID productId, String productTitle, Integer quantity, BigDecimal unitPrice, BigDecimal subtotal) {
        this.id = id;
        this.productId = productId;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
    }

    private void calculateSubtotal() {
        this.subtotal = this.unitPrice.multiply(new BigDecimal(this.quantity));
    }

    private void validate() {
        if (productId == null) {
            throw new InvalidOrderItemException("Product Id is required");
        }
        if (productTitle == null || productTitle.trim().isEmpty()) {
            throw new InvalidOrderItemException("Product Title is required");
        }
        if (quantity == null || quantity < 1) {
            throw new InvalidOrderItemException("Quantity is required");
        }
        if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidOrderItemException("Unit Price is required");
        }
    }
}
