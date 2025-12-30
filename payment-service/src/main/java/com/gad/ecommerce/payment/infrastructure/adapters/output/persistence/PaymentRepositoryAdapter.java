package com.gad.ecommerce.payment.infrastructure.adapters.output.persistence;

import com.gad.ecommerce.payment.application.ports.output.PaymentRepositoryPort;
import com.gad.ecommerce.payment.domain.model.Payment;
import com.gad.ecommerce.payment.infrastructure.adapters.output.persistence.entities.PaymentEntity;
import com.gad.ecommerce.payment.infrastructure.adapters.output.persistence.mapper.PaymentMapper;
import com.gad.ecommerce.payment.infrastructure.adapters.output.persistence.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentRepositoryAdapter implements PaymentRepositoryPort {
    private final PaymentRepository  paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public Payment savePayment(Payment payment) {
        PaymentEntity paymentEntity = paymentMapper.toEntity(payment);
        PaymentEntity paymentEntitySaved = paymentRepository.save(paymentEntity);
        return paymentMapper.toDomain(paymentEntitySaved);
    }
}
