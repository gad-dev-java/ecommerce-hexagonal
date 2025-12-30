package com.gad.ecommerce.payment.application.ports.input.dto;

import com.gad.ecommerce.payment.domain.enums.PaymentMethod;
import com.gad.ecommerce.payment.domain.enums.PaymentStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record PaymentDto(
        UUID id,
        UUID orderId,
        UUID userId,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        PaymentStatus status,
        String transactionId,
        String paymentUrl,
        LocalDateTime createdAt
) {
}
