package com.gad.ecommerce.payment.infrastructure.adapters.output.persistence.mapper;

import com.gad.ecommerce.payment.domain.model.Payment;
import com.gad.ecommerce.payment.infrastructure.adapters.output.persistence.entities.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.WARN)
public interface PaymentMapper {
    Payment toDomain(PaymentEntity paymentEntity);
    PaymentEntity toEntity(Payment payment);
}
