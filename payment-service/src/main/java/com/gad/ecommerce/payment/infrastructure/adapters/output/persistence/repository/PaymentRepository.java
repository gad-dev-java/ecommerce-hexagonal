package com.gad.ecommerce.payment.infrastructure.adapters.output.persistence.repository;

import com.gad.ecommerce.payment.infrastructure.adapters.output.persistence.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentEntity, UUID> {
}
