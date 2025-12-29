package com.gad.ecommerce.order.infrastructure.adapters.output.persistence.entities;

import com.gad.ecommerce.order.domain.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "buyer_id")
    private UUID buyerId;

    @Column(name = "seller_id")
    private UUID sellerId;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "shipping_cost")
    private BigDecimal shippingCost;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "shipping_address_snapshot", columnDefinition = "jsonb")
    private ShippingAddressSnapshot shippingAddress;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> items = new ArrayList<>();
}
