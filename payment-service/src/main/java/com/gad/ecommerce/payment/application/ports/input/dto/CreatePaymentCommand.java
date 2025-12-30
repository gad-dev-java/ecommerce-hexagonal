package com.gad.ecommerce.payment.application.ports.input.dto;

import com.gad.ecommerce.payment.domain.enums.PaymentMethod;
import com.gad.ecommerce.payment.domain.enums.PaymentStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record CreatePaymentCommand(
        UUID orderId,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        PaymentStatus status,
        String title,
        String email
) {
}
