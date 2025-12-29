package com.gad.ecommerce.order.infrastructure.adapters.output.persistence.repository;

import com.gad.ecommerce.order.infrastructure.adapters.output.persistence.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
}
