package com.gad.ecommerce.order.domain.model;

import com.gad.ecommerce.order.domain.enums.OrderStatus;
import com.gad.ecommerce.order.domain.exception.InvalidOrderException;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@ToString
public class Order {
    private UUID id;
    private String orderNumber;
    private UUID buyerId;
    private UUID sellerId;
    private BigDecimal totalAmount;
    private BigDecimal shippingCost;
    private OrderStatus status;
    private ShippingAddress shippingAddress;
    private LocalDateTime createdAt;
    private List<OrderItem> items;

    public Order(UUID buyerId, UUID sellerId, ShippingAddress shippingAddress) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.totalAmount = BigDecimal.ZERO;
        this.status = OrderStatus.PENDING;
        this.shippingAddress = shippingAddress;
        this.createdAt = LocalDateTime.now();
        this.orderNumber = generateOrderNumber();
        this.items = new ArrayList<>();
        this.validate();
    }

    @Builder
    public Order(UUID id, String orderNumber, UUID buyerId, UUID sellerId, BigDecimal totalAmount, BigDecimal shippingCost, OrderStatus status,
                 ShippingAddress shippingAddress, LocalDateTime createdAt, List<OrderItem> items) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.totalAmount = totalAmount;
        this.shippingCost = shippingCost;
        this.status = status;
        this.shippingAddress = shippingAddress;
        this.createdAt = createdAt;
        this.items = items;
    }

    public void addItem(OrderItem item) {
        if (this.status != OrderStatus.PENDING) {
            throw new InvalidOrderException("Cannot add item to order that has been pending");
        }
        if (item == null) {
             throw new InvalidOrderException("item cannot be null");
        }
        this.items.add(item);
        calculateTotal();
    }

    private void calculateTotal() {
        this.totalAmount = this.items.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void confirmOrder() {
        this.status = OrderStatus.CONFIRMED;
    }

    private String generateOrderNumber() {
        return "ORD-"  + LocalDate.now().getYear() + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private void validate(){
        if (orderNumber == null || orderNumber.trim().isEmpty()) {
            throw new InvalidOrderException("Order number cannot be empty");
        }
        if (totalAmount == null || totalAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidOrderException("Total amount cannot be negative");
        }
        if (shippingAddress == null) {
            throw new InvalidOrderException("Shipping address cannot be null");
        }
        if (buyerId == null) {
            throw new InvalidOrderException("Buyer id cannot be null");
        }
        if (sellerId == null) {
            throw new InvalidOrderException("Seller id cannot be null");
        }
    }
}
