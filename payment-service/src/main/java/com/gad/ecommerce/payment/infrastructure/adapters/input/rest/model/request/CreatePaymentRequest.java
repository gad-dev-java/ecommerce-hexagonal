package com.gad.ecommerce.payment.infrastructure.adapters.input.rest.model.request;

import com.gad.ecommerce.payment.domain.enums.PaymentMethod;
import com.gad.ecommerce.payment.domain.enums.PaymentStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record CreatePaymentRequest(
        UUID orderId,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        PaymentStatus status,
        String title,
        String email
) {
}
