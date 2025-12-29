package com.gad.ecommerce.order.infrastructure.adapters.output.persistence.repository;

import com.gad.ecommerce.order.infrastructure.adapters.output.persistence.entities.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, UUID> {
}
