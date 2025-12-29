package com.gad.ecommerce.order.infrastructure.adapters.output.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "product_title")
    private String productTitle;

    private Integer quantity;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    private BigDecimal subtotal;
}
